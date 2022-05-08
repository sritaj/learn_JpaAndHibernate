package in.sritaj.jpaandhibernate;

import in.sritaj.jpaandhibernate.entity.softdelete.Person;
import in.sritaj.jpaandhibernate.jdbcRepository.PersonJdbcDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import java.util.Date;
import java.util.List;

/**
 * SpringBootApplication Initialisation for JDBCApplication
 */
//@SpringBootApplication
public class JDBCApplication implements CommandLineRunner {

    public JDBCApplication(PersonJdbcDao personJdbcDao) {
        this.personJdbcDao = personJdbcDao;
    }

    public static void main(String[] args) {
        SpringApplication.run(JDBCApplication.class, args);
    }

    @Autowired
    public final PersonJdbcDao personJdbcDao;

    /**
     * Implemented Command Line Runner interface method for required JDBC data initialization on Application Run
     *
     * @param args - Args required
     */
    @Override
    public void run(String... args) throws Exception {
        List<Person> personList = personJdbcDao.findAll();

        for (Person p : personList) {
            System.out.println(p);
        }

        Person person = personJdbcDao.findPersonById(10002);
        System.out.println(person);

        int rowAffected = personJdbcDao.deletePersonById(10001);
        System.out.println(rowAffected);

        int userCreated = personJdbcDao.insert(new Person(10003, "sp", "bengaluru", new Date()));
        System.out.println(userCreated);

        int userUpdated = personJdbcDao.update(new Person(10003, "anushaks", "nanjangud", new Date()));
        System.out.println(userUpdated);


    }

}
