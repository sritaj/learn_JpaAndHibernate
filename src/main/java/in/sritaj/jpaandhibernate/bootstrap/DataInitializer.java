package in.sritaj.jpaandhibernate.bootstrap;

import in.sritaj.jpaandhibernate.entity.Person;
import in.sritaj.jpaandhibernate.jpa.PersonJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    PersonJpaRepository personJpaRepositoy;

    public DataInitializer(PersonJpaRepository personJpaRepositoy) {
        this.personJpaRepositoy = personJpaRepositoy;
    }

    @Override
    public void run(String... args) throws Exception {

        Person personFromJPARepo = personJpaRepositoy.findById(10001);
        System.out.println(personFromJPARepo);


    }
}
