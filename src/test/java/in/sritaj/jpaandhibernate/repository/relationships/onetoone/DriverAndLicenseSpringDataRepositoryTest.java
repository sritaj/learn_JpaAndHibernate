package in.sritaj.jpaandhibernate.repository.relationships.onetoone;

import com.github.javafaker.Faker;
import in.sritaj.jpaandhibernate.entity.relationships.onetoone.Driver;
import in.sritaj.jpaandhibernate.entity.relationships.onetoone.License;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.Date;


@SpringBootTest
public class DriverAndLicenseSpringDataRepositoryTest extends AbstractTestNGSpringContextTests {

    @Autowired
    DriverSpringDataRepository driverSpringDataRepository;

    @Autowired
    LicenseSpringDataRepository licenseSpringDataRepository;

    private Faker fs = new Faker();

    private Driver driver;
    private License license;

    @Test
    public void validateCreationOfDriverAndLicense() {
        license = new License();
        license.setType("BIKE");
        license.setValid_from(new Date());
        license.setValid_to(new Date());

        driver = new Driver();
        driver.setFirstName(fs.name().firstName());
        driver.setLastName(fs.name().lastName());
        driver.setAge(29);

        license.setDriver(driver);

        License insertedData = licenseSpringDataRepository.save(license);

        Assert.assertNotNull(insertedData);
    }

    @AfterMethod
    public void cleanDB() {
        licenseSpringDataRepository.deleteAll();
    }
}
