package in.sritaj.jpaandhibernate.repository;

import in.sritaj.jpaandhibernate.entity.inheritancemapping.tableperclass.Transport;
import org.springframework.data.repository.CrudRepository;

/**
 * TransportSpringDataRepository interface for extending CrudRepository for CRUD operations on Transport Entity using SpringJPA
 */
public interface TransportSpringDataRepository extends CrudRepository<Transport, Integer> {

}
