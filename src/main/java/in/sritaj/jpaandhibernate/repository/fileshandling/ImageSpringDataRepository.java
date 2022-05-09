package in.sritaj.jpaandhibernate.repository.fileshandling;

import in.sritaj.jpaandhibernate.entity.fileshandling.Image;
import org.springframework.data.repository.CrudRepository;

/**
 * ImageSpringDataRepository interface for extending CrudRepository for CRUD operations on Image Entity using SpringJPA
 */
public interface ImageSpringDataRepository extends CrudRepository<Image, Integer> {
}
