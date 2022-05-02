package in.sritaj.jpaandhibernate.repository;

import in.sritaj.jpaandhibernate.entity.Course;
import in.sritaj.jpaandhibernate.entity.Passport;
import in.sritaj.jpaandhibernate.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
@Transactional
public class StudentRepository {

    //Connection to database
    @Autowired
    EntityManager entityManager;

    /**
     * Method to create student with Passport
     *
     * @param passport - passport entity
     * @param student  - student entity
     */
    public void saveStudentWithPassport(Passport passport, Student student) {
        entityManager.persist(passport);
        student.setPassport(passport);
        entityManager.persist(student);
    }

    /**
     * Method to fetch Student based on id
     *
     * @param id - specific id
     * @return Student - specified Student
     */
    public Student findById(Long id) {
        return entityManager.find(Student.class, id);
    }

    /**
     * Method to retrieve Student and Courses
     *
     * @param id - specific id
     * @return Hashmap - returns Hashmap with Student Name as Key  and List Of Courses as Value
     */
    public HashMap<String, List<String>> retrieveStudentAndCourses(Long id) {
        Student student = entityManager.find(Student.class, id);
        List<Course> course = student.getCourseList();
        List<String> titles = new ArrayList<>();
        for (Course c : course) {
            titles.add(c.getCourseName());
        }
        HashMap<String, List<String>> details = new HashMap<>();
        details.put(student.getName(), titles);
        return details;
    }

    public void insertStudentAndCourses(Student student, Course course){
        entityManager.persist(course);
        entityManager.persist(student);
        student.setCourse(course);
        course.setStudent(student);
        entityManager.persist(student);
    }

}
