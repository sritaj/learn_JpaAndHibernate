package in.sritaj.jpaandhibernate.repository.inheritancemapping.singletable;

import in.sritaj.jpaandhibernate.entity.inheritancemapping.singletable.Payment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * PaymentSpringDataRepository interface for extending CrudRepository for CRUD operations on Product Entity using SpringJPA
 */
public interface PaymentSpringDataRepository extends CrudRepository<Payment, Integer> {

    @Query(value = "select payment_mode from payment", nativeQuery = true)
    List<Object[]> findAllPaymentNQ();

}
