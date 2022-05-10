package in.sritaj.jpaandhibernate.repository.ehcache;

import in.sritaj.jpaandhibernate.entity.encache.Bar;
import org.springframework.data.repository.CrudRepository;

/**
 * BarSpringDataRepository interface for extending JPARepository for CRUD operations on Foo Entity using SpringJPA
 */
public interface BarSpringDataRepository extends CrudRepository<Bar, Integer> {
}
