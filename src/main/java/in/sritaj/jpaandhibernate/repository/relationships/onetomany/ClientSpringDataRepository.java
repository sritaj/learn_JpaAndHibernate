package in.sritaj.jpaandhibernate.repository.relationships.onetomany;

import in.sritaj.jpaandhibernate.entity.relationships.onetomany.Client;
import org.springframework.data.repository.CrudRepository;

/**
 * ClientSpringDataRepository interface for extending CRUDRepository for CRUD operations on Client Entity using SpringJPA
 */
public interface ClientSpringDataRepository extends CrudRepository<Client, Integer> {
}
