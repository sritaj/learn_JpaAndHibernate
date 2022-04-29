package in.sritaj.jpaandhibernate.repository;

import in.sritaj.jpaandhibernate.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Objects;

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
    public Course findById(Long id) {
        return entityManager.find(Course.class, id);
    }

    /**
     * Method to delete Course if present
     *
     * @param id - specific id
     * @return String - message based on deletion of existing record or not finding the record
     */
    public String deleteCourse(Long id) {
        Course course = findById(id);
        if (Objects.isNull(course)) {
            return "Can't find Course for ID " + id;
        }
        entityManager.remove(course);
        return "Course removed";
    }
}
