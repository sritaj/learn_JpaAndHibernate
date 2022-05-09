package in.sritaj.jpaandhibernate.repository.customids.compositekeysembedded;

import in.sritaj.jpaandhibernate.entity.customids.compositekeysembedded.Pilot;
import in.sritaj.jpaandhibernate.entity.customids.compositekeysembedded.PilotID;
import org.springframework.data.repository.CrudRepository;

/**
 * PilotSpringDataRepository interface for extending CrudRepository for CRUD operations on Pilot Entity using SpringJPA
 */
public interface PilotSpringDataRepository extends CrudRepository<Pilot, PilotID> {
}
