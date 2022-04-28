package in.sritaj.jpaandhibernate.jdbcRepository;

import in.sritaj.jpaandhibernate.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * PersonJdbcDao class for implementing SQL queries/transactions for Person class using SpringJDBC
 */
@Repository
public class PersonJdbcDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * Method to get List of Persons from the DB
     *
     * @return List<Person>
     */
    public List<Person> findAll() {
        return jdbcTemplate.query("select * from person", new BeanPropertyRowMapper<Person>(Person.class));
    }

    /**
     * Method to fetch Person based on id
     *
     * @param id - specific id
     * @return Person - specified person
     */
    public Person findPersonById(int id) {
        return jdbcTemplate.queryForObject("select * from person where id=?", new Object[]{id},
                new BeanPropertyRowMapper<Person>(Person.class));
    }

    /**
     * Method to delete Person based on id
     *
     * @param id - specific id
     * @return int - count of the person deleted
     */
    public int deletePersonById(int id) {
        return jdbcTemplate.update("delete from person where id=?", new Object[]{id});
    }

    /**
     * Method to insert a Person into the DB
     *
     * @param person - Person object with specified params required for DB columns
     * @return int - count of the person added
     */
    public int insert(Person person) {
        return jdbcTemplate.update("Insert into PERSON(id, name, location, birth_date)\n" +
                        "values(?, ?, ?, ?)",
                new Object[]
                        {person.getId(), person.getName(), person.getLocation(), new Timestamp(person.getBirthdate().getTime())});
    }

    /**
     * Method to update a Person in the DB
     *
     * @param person - Person object with specified params required for DB columns
     * @return int - count of the person updated
     */
    public int update(Person person) {
        return jdbcTemplate.update("update PERSON set name = ?, location =?, birth_date = ?"
                        + "where id =?",
                new Object[]
                        {person.getName(), person.getLocation(), new Timestamp(person.getBirthdate().getTime()), person.getId()});
    }
}
