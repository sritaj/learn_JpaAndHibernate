package in.sritaj.jpaandhibernate.repository.relationships.manytomany;

import in.sritaj.jpaandhibernate.entity.relationships.manytomany.Project;
import org.springframework.data.repository.CrudRepository;

/**
 * ProjectSpringDataRepository interface for extending CRUDRepository for CRUD operations on Project Entity using SpringJPA
 */
public interface ProjectSpringDataRepository extends CrudRepository<Project, Integer> {
}
