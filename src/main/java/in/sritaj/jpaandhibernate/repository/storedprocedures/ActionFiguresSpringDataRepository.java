package in.sritaj.jpaandhibernate.repository.storedprocedures;

import in.sritaj.jpaandhibernate.entity.storedprocedures.ActionFigures;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ActionFiguresSpringDataRepository extends CrudRepository<ActionFigures, Integer> {

    @Query(value = "CALL GetAllActionFigures", nativeQuery = true)
    List<ActionFigures> findAllActionFigures();

    @Query(value = "CALL GetAllActionFiguresByPrice (:price_in)", nativeQuery = true)
    List<ActionFigures> findAllActionFiguresByPrice(double price_in);

    @Query(value = "CALL GetAllActionFiguresByPrice (:price_in)", nativeQuery = true)
    int findAllActionFiguresCountByPrice(double price_in);
}
