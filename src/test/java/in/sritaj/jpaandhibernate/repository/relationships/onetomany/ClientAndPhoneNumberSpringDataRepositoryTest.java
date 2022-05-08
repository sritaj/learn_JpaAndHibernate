package in.sritaj.jpaandhibernate.repository.relationships.onetomany;

import com.github.javafaker.Faker;
import in.sritaj.jpaandhibernate.entity.relationships.onetomany.Client;
import in.sritaj.jpaandhibernate.entity.relationships.onetomany.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@SpringBootTest
public class ClientAndPhoneNumberSpringDataRepositoryTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private ClientSpringDataRepository clientSpringDataRepository;

    @Autowired
    private PhoneNumberSpringDataRepository phoneNumberSpringDataRepository;

    private Faker fs = new Faker();
    private PhoneNumber phoneNumber;
    private Client newClient;
    private String phoneNo;
    private final String updatedClientName = "Mark Ruffalo";
    private final String updatedPhoneNo = "73848482393";

    @BeforeMethod
    public void setupData() {
        phoneNo = fs.phoneNumber().phoneNumber();
        phoneNumber = new PhoneNumber(phoneNo, "Home");
        newClient = new Client(fs.funnyName().name());
        newClient.addPhoneNumber(phoneNumber);
        clientSpringDataRepository.save(newClient);
    }

    @Test(testName = "Validate Creation of Client with Phone Number")
    public void validateClientCreationWithPhoneNumber() {

        Optional<Client> insertedClient = clientSpringDataRepository.findById(newClient.getID());

        Assert.assertNotNull(insertedClient);
    }

    @Test(testName = "Validate Retrieval of Client with Phone Number")
    public void validateCustomerAndPhoneNumberRetrieval() {

        Optional<Client> insertedClient = clientSpringDataRepository.findById(newClient.getID());
        Set<PhoneNumber> insertedPhoneNumber = insertedClient.get().getPhoneNumbers();
        List<String> result = new ArrayList<>();
        insertedPhoneNumber.forEach(n -> result.add(n.getNumber()));

        Assert.assertEquals(newClient.getName(), newClient.getName());
        Assert.assertEquals(result.get(0), phoneNo);
    }

    @Test(testName = "Validate Updation of Client Name and Phone Number")
    public void validateUpdationOfCustomerNameAndPhoneNumber() {

        newClient.setName(updatedClientName);
        phoneNumber.setNumber(updatedPhoneNo);
        clientSpringDataRepository.save(newClient);

        Optional<Client> insertedClientPostUpdate = clientSpringDataRepository.findById(newClient.getID());
        Set<PhoneNumber> insertedPhoneNumber = insertedClientPostUpdate.get().getPhoneNumbers();
        List<String> result = new ArrayList<>();
        insertedPhoneNumber.forEach(n -> result.add(n.getNumber()));

        Assert.assertEquals(insertedClientPostUpdate.get().getName(), updatedClientName);
        Assert.assertEquals(result.get(0), updatedPhoneNo);
    }

    @Test(testName = "Validate Deletion of Client")
    public void validateDeletionOfCustomer() {

        clientSpringDataRepository.delete(newClient);
        Optional<Client> insertedClientPostUpdate = clientSpringDataRepository.findById(newClient.getID());

        Assert.assertTrue(insertedClientPostUpdate.isEmpty());
    }

    @AfterMethod
    public void clearDB() {
        clientSpringDataRepository.deleteAll();
    }
}
