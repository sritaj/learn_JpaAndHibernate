package in.sritaj.jpaandhibernate.jpa;

import in.sritaj.jpaandhibernate.entity.Person;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

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
    public Person findById(int id) {
        return entityManager.find(Person.class, id);
    }

    /**
     * Method to create Person
     *
     * @param person - Person entity
     * @return Person - created person entity
     */
    public Person insert(Person person) {
        return entityManager.merge(person);
    }

    /**
     * Method to update Person if present else create a new one
     *
     * @param id     - specific id
     * @param person - Person entity
     * @return String - message based on update of existing record or creating new record
     */
    public String update(int id, Person person) {
        if (Objects.nonNull(findById(id))) {
            entityManager.merge(person);
            return "Successfully updated record";
        }
        entityManager.merge(person);
        return "Existing record not found, created new record";
    }

    /**
     * Method to update Person if present else create a new one
     *
     * @param id - specific id
     * @return String - message based on deletion of existing record or not finding the record
     */
    public String deletePerson(int id) {
        Person person = findById(id);
        if (Objects.isNull(person)) {
            return "Can't find Person    for ID " + id;
        }
        entityManager.remove(person);
        return "Person removed";
    }

    /**
     * Method to fetch All the Person records
     *
     * @return List<People> - list of people
     */
    public List<Person> findAll(){
        TypedQuery<Person> namedQuery = entityManager.createNamedQuery("find_all_persons", Person.class);
        return namedQuery.getResultList();
    }

}
