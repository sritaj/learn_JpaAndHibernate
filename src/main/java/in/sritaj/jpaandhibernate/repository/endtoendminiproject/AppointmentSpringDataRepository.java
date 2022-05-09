package in.sritaj.jpaandhibernate.repository.endtoendminiproject;

import in.sritaj.jpaandhibernate.entity.endtoendminiproject.Appointment;
import org.springframework.data.repository.CrudRepository;

/**
 * AppointmentSpringDataRepository interface for extending CrudRepository for CRUD operations on Appointment Entity using SpringJPA
 */
public interface AppointmentSpringDataRepository extends CrudRepository<Appointment, Long> {
}
