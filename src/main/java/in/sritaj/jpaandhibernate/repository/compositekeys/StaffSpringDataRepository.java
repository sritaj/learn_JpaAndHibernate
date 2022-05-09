package in.sritaj.jpaandhibernate.repository.compositekeys;

import in.sritaj.jpaandhibernate.entity.compositekeys.Staff;
import in.sritaj.jpaandhibernate.entity.compositekeys.StaffID;
import org.springframework.data.repository.CrudRepository;

/**
 * StaffSpringDataRepository interface for extending CrudRepository for CRUD operations on Staff Entity using SpringJPA
 */
public interface StaffSpringDataRepository extends CrudRepository<Staff, StaffID> {
}
