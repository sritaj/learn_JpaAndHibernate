package in.sritaj.jpaandhibernate.repository;

import in.sritaj.jpaandhibernate.entity.Product;
import org.springframework.data.repository.CrudRepository;

/**
 * ProductSpringDataRepository interface for extending CrudRepository for CRUD operations on Product Entity using SpringJPA
 */
public interface ProductSpringDataRepository extends CrudRepository<Product, Integer> {
}
