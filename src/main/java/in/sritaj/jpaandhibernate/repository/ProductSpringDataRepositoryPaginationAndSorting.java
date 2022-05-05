package in.sritaj.jpaandhibernate.repository;

import in.sritaj.jpaandhibernate.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * ProductSpringDataRepository interface for extending PagingAndSortingRepository for Paging and Sorting operations on Product Entity using SpringJPA
 */
public interface ProductSpringDataRepositoryPaginationAndSorting extends PagingAndSortingRepository<Product, Integer> {

    List<Product> findByIdIn(List<Integer> ids, Pageable pageable);
}
