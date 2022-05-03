package in.sritaj.jpaandhibernate.repository;

import in.sritaj.jpaandhibernate.entity.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookSpringDataRepository extends CrudRepository<Book, Long> {
}
