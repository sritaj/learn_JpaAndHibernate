package in.sritaj.jpaandhibernate.repository.entitymanager;

import in.sritaj.jpaandhibernate.entity.entitymanager.Passport;
import in.sritaj.jpaandhibernate.entity.entitymanager.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

/**
 * PassportRepository class for implementing SQL queries/transactions for Passport Entity using Hibernate and JPA
 */
@Repository
@Transactional
public class PassportRepository {

    //Connection to database
    @Autowired
    private EntityManager entityManager;

    /**
     * Method to create Passport with student
     *
     * @param passport - passport entity
     * @param student  - student entity
     */
    public void savePassportWithStudent(Student student, Passport passport) {
        entityManager.persist(student);
        passport.setStudent(student);
        entityManager.persist(passport);
    }

    /**
     * Method to fetch Passport based on id
     *
     * @param id - specific id
     * @return Passport - specified Passport
     */
    public Passport findById(Long id) {
        return entityManager.find(Passport.class, id);
    }

}
