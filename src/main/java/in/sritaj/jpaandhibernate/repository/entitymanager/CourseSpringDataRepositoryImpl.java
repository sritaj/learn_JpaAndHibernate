package in.sritaj.jpaandhibernate.repository.entitymanager;

import in.sritaj.jpaandhibernate.entity.entitymanager.Course;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * CourseSpringDataRepositoryImpl class for implementing custom methods for Course Entity using SpringJPA
 */
@Component
public class CourseSpringDataRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Method to fetch course based on name
     *
     * @param name - Course name
     * @return List - courses
     */
    public List<Course> findByName(String name) {
        Query query = entityManager.createQuery("Select c from Course c where course_name like :name", Course.class);
        query.setParameter("name", name);
        return query.getResultList();
    }
}
