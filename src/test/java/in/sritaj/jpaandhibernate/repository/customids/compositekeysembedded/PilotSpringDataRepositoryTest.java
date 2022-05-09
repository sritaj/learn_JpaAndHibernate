package in.sritaj.jpaandhibernate.repository.customids.compositekeysembedded;

import com.github.javafaker.Faker;
import in.sritaj.jpaandhibernate.entity.customids.compositekeysembedded.Pilot;
import in.sritaj.jpaandhibernate.entity.customids.compositekeysembedded.PilotID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Optional;

@SpringBootTest
public class PilotSpringDataRepositoryTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private PilotSpringDataRepository pilotSpringDataRepository;

    private Faker fs = new Faker();

    @Test
    public void validateCreationOfPilotWithCompositeKeys() {

        int id = fs.number().randomDigitNotZero();
        String email = fs.internet().emailAddress();

        PilotID pilotID = new PilotID();
        pilotID.setId(id);
        pilotID.setEmail(email);

        Pilot pilot = new Pilot();
        pilot.setPilotID(pilotID);
        pilot.setName(fs.name().fullName());

        pilotSpringDataRepository.save(pilot);

        Optional<Pilot> pilotCreated = pilotSpringDataRepository.findById(pilotID);

        Assert.assertTrue(pilotCreated.isPresent());
    }
}
