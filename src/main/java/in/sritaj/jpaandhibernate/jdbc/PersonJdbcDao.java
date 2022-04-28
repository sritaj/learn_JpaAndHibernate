package in.sritaj.jpaandhibernate.jdbc;

import in.sritaj.jpaandhibernate.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class PersonJdbcDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Person> findAll(){
        return jdbcTemplate.query("select * from person", new BeanPropertyRowMapper<Person>(Person.class));
    }

    public Person findPersonById(int id){
        return jdbcTemplate.queryForObject("select * from person where id=?", new Object[]{id},
        new BeanPropertyRowMapper<Person>(Person.class));
    }

    public int deletePersonById(int id){
        return jdbcTemplate.update("delete from person where id=?", new Object[]{id});
    }

    public int insert(Person person){
        return jdbcTemplate.update("Insert into PERSON(id, name, location, birth_date)\n" +
                "values(?, ?, ?, ?)",
                new Object[]
                        {person.getId(), person.getName(), person.getLocation(), new Timestamp(person.getBirthdate().getTime())});
    }

    public int update(Person person){
        return jdbcTemplate.update("update PERSON set name = ?, location =?, birth_date = ?"
                +"where id =?",
                new Object[]
                        {person.getName(), person.getLocation(), new Timestamp(person.getBirthdate().getTime()), person.getId()});
    }
}
