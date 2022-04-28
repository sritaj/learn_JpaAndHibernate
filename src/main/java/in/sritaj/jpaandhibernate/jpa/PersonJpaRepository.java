package in.sritaj.jpaandhibernate.jpa;

import in.sritaj.jpaandhibernate.entity.Person;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * PersonJpaRepository class for implementing SQL queries/transactions for Person class using Hibernate and JPA
 */
@Repository
@Transactional
public class PersonJpaRepository {

    //Connection to database
    @PersistenceContext
    EntityManager entityManager;

    /**
     * Method to fetch Person based on id
     *
     * @param id - specific id
     * @return Person - specified person
     */
    public Person findById(int id){
        return entityManager.find(Person.class, id);
    }
}
