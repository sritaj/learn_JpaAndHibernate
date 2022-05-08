package in.sritaj.jpaandhibernate.repository.relationships.onetoone;

import in.sritaj.jpaandhibernate.entity.relationships.onetoone.Driver;
import org.springframework.data.repository.CrudRepository;

/**
 * DriverSpringDataRepository interface for extending CRUDRepository for CRUD operations on Driver Entity using SpringJPA
 */
public interface DriverSpringDataRepository extends CrudRepository<Driver, Integer> {
}
