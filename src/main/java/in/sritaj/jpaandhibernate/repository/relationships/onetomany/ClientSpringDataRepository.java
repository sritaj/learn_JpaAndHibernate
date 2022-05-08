package in.sritaj.jpaandhibernate.repository.relationships.onetomany;

import in.sritaj.jpaandhibernate.entity.relationships.onetomany.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientSpringDataRepository extends CrudRepository<Client, Integer> {
}
