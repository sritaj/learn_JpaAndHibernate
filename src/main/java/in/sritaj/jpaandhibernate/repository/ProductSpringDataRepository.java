package in.sritaj.jpaandhibernate.repository;

import in.sritaj.jpaandhibernate.entity.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * ProductSpringDataRepository interface for extending CrudRepository for CRUD operations on Product Entity using SpringJPA
 */
public interface ProductSpringDataRepository extends CrudRepository<Product, Integer> {

    List<Product> findByName(String name);

    List<Product> findByNameAndDescription(String name, String description);

    List<Product> findByPriceGreaterThan(Double price);

    List<Product> findByDescriptionContains(String description);

    List<Product> findByPriceBetween(Double priceOne, Double priceTwo);

    List<Product> findByNameLike(String name);

    List<Product> findByIdIn(List<Integer> ids);

}
