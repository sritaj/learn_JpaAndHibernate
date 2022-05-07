package in.sritaj.jpaandhibernate.repository.fileshandling;

import in.sritaj.jpaandhibernate.entity.fileshandling.Image;
import org.springframework.data.repository.CrudRepository;

public interface ImageSpringDataRepository extends CrudRepository<Image, Integer> {
}
