package in.sritaj.jpaandhibernate.repository;

import in.sritaj.jpaandhibernate.entity.Passport;
import in.sritaj.jpaandhibernate.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

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
     * @return Course - specified Course
     */
    public Student findById(Long id) {
        return entityManager.find(Student.class, id);
    }
}
