package in.sritaj.jpaandhibernate.repository.ehcache;

import in.sritaj.jpaandhibernate.entity.encache.Foo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * FooSpringDataRepository interface for extending JPARepository for CRUD operations on Foo Entity using SpringJPA
 */
public interface FooSpringDataRepository extends JpaRepository<Foo, Integer> {
}
