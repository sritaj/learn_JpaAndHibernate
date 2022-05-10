package in.sritaj.jpaandhibernate.repository.restfulapis;

import com.github.javafaker.Faker;
import in.sritaj.jpaandhibernate.entity.restfulapis.Interest;
import in.sritaj.jpaandhibernate.entity.restfulapis.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Optional;

@SpringBootTest
public class RestfulAPISpringDataTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private UserAccountSpringDataRepository userAccountSpringDataRepository;

    @Autowired
    private InterestSpringDataRepository interestSpringDataRepository;

    private Faker fs = new Faker();
    private UserAccount userAccount;
    private Interest interest;

    @BeforeMethod
    public void setupData() {
        userAccount = new UserAccount();
        userAccount.setUserName(fs.name().username());
        userAccount.setPassword(fs.name().bloodGroup());
        userAccount.setAge(30);
        userAccount.setCity(fs.address().city());
        userAccount.setCountry("USA");
        userAccount.setPhoneNumber("12345678");
        userAccount.setGender("M");

        interest = new Interest();
        interest.setLikes(fs.harryPotter().book());
        interest.setDislikes(fs.starTrek().character());
        interest.setAbout(fs.harryPotter().spell());
        interest.setProfileUrl(fs.harryPotter().location());
        interest.setHobbies(fs.pokemon().name());
    }

    @Test(testName = "Validate Creation of User Account")
    public void validateCreationOfUserAccount() {
        UserAccount createdUser = userAccountSpringDataRepository.save(userAccount);

        Assert.assertNotNull(createdUser);
    }

    @Test(testName = "Validate Creation of Interest and Setting it for the User")
    public void validateCreationOfInterestAndSettingItForTheUser() {
        userAccountSpringDataRepository.save(userAccount);
        Optional<UserAccount> selectedUserToUpdate = userAccountSpringDataRepository.findById(userAccount.getId());
        interest.setUserAccount(selectedUserToUpdate.get());

        Interest createdInterest = interestSpringDataRepository.save(interest);

        Assert.assertNotNull(createdInterest);
    }

    @AfterTest
    public void clearDB() {
        userAccountSpringDataRepository.deleteAll();
    }

}
