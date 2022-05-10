package in.sritaj.jpaandhibernate.repository.restfulapis;

import in.sritaj.jpaandhibernate.entity.restfulapis.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * UserAccountSpringDataRepository interface for extending JpaRepository for CRUD operations on UserAccount Entity using SpringJPA
 */
public interface UserAccountSpringDataRepository extends JpaRepository<UserAccount, Integer> {

    @Query("from UserAccount where age=:age and city=:city and country=:country and id!=:id")
    List<UserAccount> findMatches(@Param("age")int age, @Param("city")String city, @Param("country")String country, @Param("id")int id);
}
