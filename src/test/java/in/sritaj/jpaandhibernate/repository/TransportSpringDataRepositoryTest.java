package in.sritaj.jpaandhibernate.repository;

import com.github.javafaker.Faker;
import in.sritaj.jpaandhibernate.entity.inheritancemapping.tableperclass.Bike;
import in.sritaj.jpaandhibernate.entity.inheritancemapping.tableperclass.Car;
import in.sritaj.jpaandhibernate.entity.inheritancemapping.tableperclass.Transport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

@SpringBootTest
public class TransportSpringDataRepositoryTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private TransportSpringDataRepository transportSpringDataRepository;

    private Faker fs = new Faker();

    @Test(testName = "Validate transport with Car")
    public void validateTransportWithCar() {
        Transport transport = new Car(147.89, fs.starTrek().location());
        Transport createdTransport = transportSpringDataRepository.save(transport);

        Assert.assertNotNull(createdTransport);

    }

    @Test(testName = "Validate transport with Bike")
    public void validateTransportWithBike() {
        Transport transport = new Bike(22.45, fs.starTrek().location());
        Transport createdTransport = transportSpringDataRepository.save(transport);

        Assert.assertNotNull(createdTransport);

    }
}
