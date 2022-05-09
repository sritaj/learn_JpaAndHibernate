package in.sritaj.jpaandhibernate.repository.endtoendminiproject;

import in.sritaj.jpaandhibernate.entity.endtoendminiproject.Doctor;
import org.springframework.data.repository.CrudRepository;

/**
 * DoctorSpringDataRepository interface for extending CrudRepository for CRUD operations on Doctor Entity using SpringJPA
 */
public interface DoctorSpringDataRepository extends CrudRepository<Doctor, Long> {
}
