package in.sritaj.jpaandhibernate.repository;

import in.sritaj.jpaandhibernate.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * CourseRepository class for implementing SQL queries/transactions for Course class using Hibernate and JPA
 */
@Repository
@Transactional
public class CourseRepository {

    //Connection to database
    @Autowired
    EntityManager entityManager;

    /**
     * Method to fetch Course based on id
     *
     * @param id - specific id
     * @return Course - specified Course
     */
    public Course findById(Long id){
        return entityManager.find(Course.class, id);
    }
}
