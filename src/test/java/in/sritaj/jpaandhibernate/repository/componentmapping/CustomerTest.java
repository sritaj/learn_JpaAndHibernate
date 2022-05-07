package in.sritaj.jpaandhibernate.repository.componentmapping;

import com.github.javafaker.Faker;
import in.sritaj.jpaandhibernate.entity.componentmapping.Customer;
import in.sritaj.jpaandhibernate.entity.componentmapping.CustomerAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

@SpringBootTest
public class CustomerTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private CustomerSpringDataRepository customerSpringDataRepository;

    private Faker fs = new Faker();

    @Test
    public void validateCustomerCreation() {
        CustomerAddress customerAddress = new CustomerAddress(
                fs.address().streetAddress(),
                fs.address().city(),
                fs.address().state(),
                fs.address().zipCode(),
                fs.address().country());
        Customer newCustomer = customerSpringDataRepository.save(new Customer(fs.funnyName().name(), customerAddress));

        Assert.assertNotNull(newCustomer);

    }
}
