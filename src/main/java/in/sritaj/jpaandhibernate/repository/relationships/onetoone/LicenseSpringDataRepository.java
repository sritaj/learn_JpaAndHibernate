package in.sritaj.jpaandhibernate.repository.relationships.onetoone;

import in.sritaj.jpaandhibernate.entity.relationships.onetoone.License;
import org.springframework.data.repository.CrudRepository;

/**
 * LicenseSpringDataRepository interface for extending CRUDRepository for CRUD operations on License Entity using SpringJPA
 */
public interface LicenseSpringDataRepository extends CrudRepository<License, Integer> {
}
