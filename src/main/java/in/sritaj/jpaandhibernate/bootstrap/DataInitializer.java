package in.sritaj.jpaandhibernate.bootstrap;

import in.sritaj.jpaandhibernate.entity.Person;
import in.sritaj.jpaandhibernate.jdbc.PersonJdbcDao;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    PersonJdbcDao personJdbcDao;

    public DataInitializer(PersonJdbcDao personJdbcDao) {
        this.personJdbcDao = personJdbcDao;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Person> personList = personJdbcDao.findAll();

        for (Person p : personList) {
            System.out.println(p);
        }

        Person person = personJdbcDao.findPersonById(10002);
        System.out.println(person);

    }
}
