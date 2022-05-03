package in.sritaj.jpaandhibernate.repository;

import in.sritaj.jpaandhibernate.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * CourseSpringDataRepository interface for extending JPARepository for CRUD operations on Course Entity using SpringJPA
 */
@RepositoryRestResource(path = "courses")
public interface CourseSpringDataRepository extends JpaRepository<Course, Long> {

     List<Course> findByName(String name);
}
