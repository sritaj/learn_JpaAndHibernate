package in.sritaj.jpaandhibernate.repository.endtoendminiproject;

import in.sritaj.jpaandhibernate.entity.endtoendminiproject.Patient;
import org.springframework.data.repository.CrudRepository;

/**
 * PatientSpringDataRepository interface for extending CrudRepository for CRUD operations on Patient Entity using SpringJPA
 */
public interface PatientSpringDataRepository extends CrudRepository<Patient, Long> {
}
