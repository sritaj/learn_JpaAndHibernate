package in.sritaj.jpaandhibernate.repository.restfulapis;

import in.sritaj.jpaandhibernate.entity.restfulapis.Interest;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * InterestSpringDataRepository interface for extending JpaRepository for CRUD operations on Interest Entity using SpringJPA
 */
public interface InterestSpringDataRepository extends JpaRepository<Interest, Integer> {
}
