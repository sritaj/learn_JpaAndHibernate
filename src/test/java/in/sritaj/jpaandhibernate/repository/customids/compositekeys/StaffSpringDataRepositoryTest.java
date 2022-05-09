package in.sritaj.jpaandhibernate.repository.customids.compositekeys;

import com.github.javafaker.Faker;
import in.sritaj.jpaandhibernate.entity.customids.compositekeys.Staff;
import in.sritaj.jpaandhibernate.entity.customids.compositekeys.StaffID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Optional;

@SpringBootTest
public class StaffSpringDataRepositoryTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private StaffSpringDataRepository staffSpringDataRepository;

    private Faker fs = new Faker();

    @Test(testName = "Validate Staff Creation With Composite ID")
    public void validateStaffCreationWithCompositeID() {
        int id = fs.number().randomDigitNotZero();
        String email = fs.internet().emailAddress();
        StaffID staffId = new StaffID();
        staffId.setId(id);
        staffId.setEmail(email);

        Staff staff = new Staff();
        staff.setId(staffId.getId());
        staff.setEmail(staffId.getEmail());
        staff.setName(fs.name().fullName());

        staffSpringDataRepository.save(staff);

        Optional<Staff> staffCreated = staffSpringDataRepository.findById(staffId);

        Assert.assertTrue(staffCreated.isPresent());

    }

    @AfterMethod
    public void clearDB() {
        staffSpringDataRepository.deleteAll();
    }

}
