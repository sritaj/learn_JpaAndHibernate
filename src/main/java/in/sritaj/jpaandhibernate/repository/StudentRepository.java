package in.sritaj.jpaandhibernate.repository;

import in.sritaj.jpaandhibernate.entity.Address;
import in.sritaj.jpaandhibernate.entity.Course;
import in.sritaj.jpaandhibernate.entity.Passport;
import in.sritaj.jpaandhibernate.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * StudentRepository class for implementing SQL queries/transactions for Student Entity using Hibernate and JPA
 */
@Repository
@Transactional
public class StudentRepository {

    //Connection to database
    @Autowired
    EntityManager entityManager;

    private final String selectStudentWithPassport = "Select s from Student s where s.passport.passportID like :q";


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

    /**
     * Method to insert Student and Course
     *
     * @param student - student entity
     * @param course  - course entity
     */
    public void insertStudentAndCourse(Student student, Course course) {
        entityManager.persist(course);
        entityManager.persist(student);
        student.setCourse(course);
        course.setStudent(student);
        entityManager.persist(student);
    }

    /**
     * Method to retrieve Students having Passport with specific pattern using JPQL typed query
     *
     * @return List<Student> - Student
     */
    public List<Student> selectStudentWithMatchingPassportPattern(String passportString) {
        TypedQuery<Student> typedQuery = entityManager.createQuery(selectStudentWithPassport, Student.class);
        typedQuery.setParameter("q", passportString);
        return typedQuery.getResultList();
    }

    /**
     * Method to add Student Address
     *
     * @param ID      - student ID
     * @param address - Address Details
     */
    public void setStudentAddress(Long ID, Address address) {
        Student student = entityManager.find(Student.class, ID);
        student.setAddress(address);
        entityManager.persist(student);
    }
}
