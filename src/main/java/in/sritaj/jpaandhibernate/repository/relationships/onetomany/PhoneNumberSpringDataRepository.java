package in.sritaj.jpaandhibernate.repository.relationships.onetomany;

import in.sritaj.jpaandhibernate.entity.relationships.onetomany.PhoneNumber;
import org.springframework.data.repository.CrudRepository;

/**
 * PhoneNumberSpringDataRepository interface for extending CRUDRepository for CRUD operations on PhoneNumber Entity using SpringJPA
 */
public interface PhoneNumberSpringDataRepository extends CrudRepository<PhoneNumber, Integer> {
}
