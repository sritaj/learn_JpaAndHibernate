package in.sritaj.jpaandhibernate.repository.relationships.manytomany;

import in.sritaj.jpaandhibernate.entity.relationships.manytomany.Programmer;
import org.springframework.data.repository.CrudRepository;

/**
 * ProgrammerSprintDataRepository interface for extending CRUDRepository for CRUD operations on Programmer Entity using SpringJPA
 */
public interface ProgrammerSprintDataRepository extends CrudRepository<Programmer, Integer> {
}
