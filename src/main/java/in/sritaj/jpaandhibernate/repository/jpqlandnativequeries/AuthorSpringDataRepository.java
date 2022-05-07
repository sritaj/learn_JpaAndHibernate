package in.sritaj.jpaandhibernate.repository.jpqlandnativequeries;

import in.sritaj.jpaandhibernate.entity.jpqlandnativequeries.Author;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * AuthorSpringDataRepository interface for extending CrudRepository for CRUD operations on Author Entity using SpringJPA
 */
public interface AuthorSpringDataRepository extends CrudRepository<Author, Integer> {

    @Query("from Author")
    List<Author> findAllAuthors();

    @Query("Select firstName, lastName from Author A")
    List<Author> findAllAuthorsPartialData();

    @Query("from Author where firstName = :firstName")
    List<Author> findAllAuthorsByFirstName(@Param("firstName") String firstName);

    @Query("from Author where booksPublished > :min and booksPublished < :max")
    List<Author> findAuthorsForGivenPublishedBooks(@Param("min") int min, @Param("max") int max);

    @Modifying
    @Transactional
    @Query("delete from Author where firstName = :firstName")
    void deleteAuthorByfirstName(@Param("firstName") String firstName);

    @Query("from Author")
    List<Author> findAllAuthors(Pageable pageable);

    @Query(value = "select * from author", nativeQuery = true)
    List<Author> findAllAuthorsNQ();

    @Query(value = "select * from author where first_name = :firstName", nativeQuery = true)
    List<Author> findAllAuthorsByFirstNameNQ(@Param("firstName") String firstName);

}
