package in.sritaj.jpaandhibernate.repository.transactionmanagement;

import in.sritaj.jpaandhibernate.entity.transactionmanagement.BankAccount;
import org.springframework.data.repository.CrudRepository;

/**
 * BankAccountSpringDataRepository interface for extending CRUDRepository for CRUD operations on BankAccount Entity using SpringJPA
 */
public interface BankAccountSpringDataRepository extends CrudRepository<BankAccount, Integer> {
}