package in.sritaj.jpaandhibernate.repository;

import in.sritaj.jpaandhibernate.entity.componentmapping.Customer;
import org.springframework.data.repository.CrudRepository;

/**
 * CustomerSpringDataRepository interface for extending CrudRepository for CRUD operations on Customer Entity using SpringJPA
 */
public interface CustomerSpringDataRepository extends CrudRepository<Customer, Integer> {
}
