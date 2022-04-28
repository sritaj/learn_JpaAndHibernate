package in.sritaj.jpaandhibernate.bootstrap;

import in.sritaj.jpaandhibernate.entity.Person;
import in.sritaj.jpaandhibernate.jpa.PersonJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * DataInitializer class for initializing data for running the Application
 */
@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    PersonJpaRepository personJpaRepositoy;

    public DataInitializer(PersonJpaRepository personJpaRepositoy) {
        this.personJpaRepositoy = personJpaRepositoy;
    }

    /**
     * Implemented Command Line Runner interface method for required JDBC data initialization on Application Run
     *
     * @param args - Args required
     */
    @Override
    public void run(String... args) throws Exception {

        Person personFromJPARepo = personJpaRepositoy.findById(10001);
        System.out.println(personFromJPARepo);

        Person newlyCreatedPerson = personJpaRepositoy.insert(new Person("Harry", "London", new Date()));
        System.out.println(newlyCreatedPerson);
        System.out.println(newlyCreatedPerson.getId());

        newlyCreatedPerson.setName("Harry Potter");
        String updatedPerson =  personJpaRepositoy.update(newlyCreatedPerson.getId(),newlyCreatedPerson);
        System.out.println(updatedPerson);

        String newlyCreatedPersonWhenIDDoesntExist = personJpaRepositoy.update(123456 , new Person("Ron", "London", new Date()));
        System.out.println(newlyCreatedPersonWhenIDDoesntExist);

        String personDeleteWhenIDDoesntExist = personJpaRepositoy.deletePerson(10393);
        System.out.println(personDeleteWhenIDDoesntExist);

        Person newlyCreatedPersonForDelete = personJpaRepositoy.insert(new Person("Malfoy", "London", new Date()));
        String personDeleted = personJpaRepositoy.deletePerson(newlyCreatedPersonForDelete.getId());
        System.out.println(personDeleted);


    }
}
