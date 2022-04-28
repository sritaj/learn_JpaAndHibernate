package in.sritaj.jpaandhibernate.bootstrap;

import com.github.javafaker.Faker;
import in.sritaj.jpaandhibernate.entity.Person;
import in.sritaj.jpaandhibernate.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * DataInitializer class for initializing data for running the Application
 */
@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    PersonRepository personRepository;

    Faker fs = new Faker();

    public DataInitializer(PersonRepository personJpaRepositoy) {
        this.personRepository = personJpaRepositoy;
    }

    /**
     * Implemented Command Line Runner interface method for required JDBC data initialization on Application Run
     *
     * @param args - Args required
     */
    @Override
    public void run(String... args) throws Exception {

        Person personFromJPARepo = personRepository.findById(10001);
        System.out.println(personFromJPARepo);

        Person newlyCreatedPerson = personRepository.insert(new Person(fs.name().fullName(), fs.address().cityName(), new Date()));
        System.out.println(newlyCreatedPerson);
        System.out.println(newlyCreatedPerson.getId());

        newlyCreatedPerson.setName(fs.name().fullName());
        String updatedPerson =  personRepository.update(newlyCreatedPerson.getId(),newlyCreatedPerson);
        System.out.println(updatedPerson);

        String newlyCreatedPersonWhenIDDoesntExist = personRepository.update(123456 , new Person(fs.name().fullName(), fs.address().cityName(), new Date()));
        System.out.println(newlyCreatedPersonWhenIDDoesntExist);

        String personDeleteWhenIDDoesntExist = personRepository.deletePerson(10393);
        System.out.println(personDeleteWhenIDDoesntExist);

        Person newlyCreatedPersonForDelete = personRepository.insert(new Person(fs.name().fullName(), fs.address().cityName(), new Date()));
        String personDeleted = personRepository.deletePerson(newlyCreatedPersonForDelete.getId());
        System.out.println(personDeleted);

        List<Person> allPersons = personRepository.findAll();
        allPersons.forEach(p -> System.out.println(p.getName()));


    }
}
