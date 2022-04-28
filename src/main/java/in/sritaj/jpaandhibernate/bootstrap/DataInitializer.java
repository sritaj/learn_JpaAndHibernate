package in.sritaj.jpaandhibernate.bootstrap;

import in.sritaj.jpaandhibernate.entity.Person;
import in.sritaj.jpaandhibernate.jdbc.PersonJdbcDao;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
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

        int rowAffected = personJdbcDao.deletePersonById(10001);
        System.out.println(rowAffected);

        int userCreated = personJdbcDao.insert(new Person(10003, "sp", "bengaluru", new Date()));
        System.out.println(userCreated);

        int userUpdated = personJdbcDao.update(new Person(10003, "anushaks", "nanjangud", new Date()));
        System.out.println(userUpdated);
        
    }
}
