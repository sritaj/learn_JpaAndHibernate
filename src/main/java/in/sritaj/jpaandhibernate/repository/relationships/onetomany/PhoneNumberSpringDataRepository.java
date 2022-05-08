package in.sritaj.jpaandhibernate.repository.relationships.onetomany;

import in.sritaj.jpaandhibernate.entity.relationships.onetomany.PhoneNumber;
import org.springframework.data.repository.CrudRepository;

public interface PhoneNumberSpringDataRepository extends CrudRepository<PhoneNumber, Integer> {
}
