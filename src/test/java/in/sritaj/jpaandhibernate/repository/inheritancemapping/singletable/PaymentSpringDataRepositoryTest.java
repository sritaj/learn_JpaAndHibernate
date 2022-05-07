package in.sritaj.jpaandhibernate.repository.inheritancemapping.singletable;

import com.github.javafaker.Faker;
import in.sritaj.jpaandhibernate.entity.inheritancemapping.singletable.Cheque;
import in.sritaj.jpaandhibernate.entity.inheritancemapping.singletable.CreditCard;
import in.sritaj.jpaandhibernate.entity.inheritancemapping.singletable.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

@SpringBootTest
public class PaymentSpringDataRepositoryTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private PaymentSpringDataRepository paymentSpringDataRepository;

    private Faker fs = new Faker();

    @Test(testName = "Validate Payment using Credit Card")
    public void validatePaymentUsingCreditCard() {
        paymentSpringDataRepository.deleteAll();
        Payment cc = new CreditCard(237.99, fs.business().creditCardNumber());
        paymentSpringDataRepository.save(cc);
        List<Object[]> paymentMode = paymentSpringDataRepository.findAllPaymentNQ();
        String actualPaymentType = paymentMode.get(0)[0].toString();

        Assert.assertEquals(actualPaymentType, "CC");

    }

    @Test(testName = "Validate Payment using Cheque")
    public void validatePaymentUsingCheque() {
        paymentSpringDataRepository.deleteAll();
        Payment cc = new Cheque(100.99, fs.business().creditCardNumber());
        paymentSpringDataRepository.save(cc);
        List<Object[]> paymentMode = paymentSpringDataRepository.findAllPaymentNQ();
        String actualPaymentType = paymentMode.get(0)[0].toString();

        Assert.assertEquals(actualPaymentType, "CH");

    }
}
