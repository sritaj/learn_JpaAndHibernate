package in.sritaj.jpaandhibernate.repository;

import in.sritaj.jpaandhibernate.entity.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductSpringDataRepositoryPaginationAndSorting extends PagingAndSortingRepository<Product, Integer> {
}
