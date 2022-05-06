package in.sritaj.jpaandhibernate.repository;

import in.sritaj.jpaandhibernate.entity.idautogeneration.Book;
import org.springframework.data.repository.CrudRepository;

/**
 * BookSpringDataRepository interface for extending CrudRepository for CRUD operations on Book Entity using SpringJPA
 */
public interface BookSpringDataRepository extends CrudRepository<Book, Long> {
}
