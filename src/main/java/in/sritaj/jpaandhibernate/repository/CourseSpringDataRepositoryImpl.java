package in.sritaj.jpaandhibernate.repository;

import in.sritaj.jpaandhibernate.entity.Course;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
public class CourseSpringDataRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Course> findByName(String name) {
        Query query = entityManager.createQuery("Select c from Course c where course_name like :name", Course.class);
        query.setParameter("name", name);
        return query.getResultList();
    }
}
