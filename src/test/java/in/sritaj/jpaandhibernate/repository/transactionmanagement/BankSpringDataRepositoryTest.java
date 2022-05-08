package in.sritaj.jpaandhibernate.repository.transactionmanagement;

import com.github.javafaker.Faker;
import in.sritaj.jpaandhibernate.entity.transactionmanagement.BankAccount;
import in.sritaj.jpaandhibernate.service.transactionmanagement.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class BankSpringDataRepositoryTest extends AbstractTestNGSpringContextTests {

    @Autowired
    BankAccountService bankAccountService;

    @Autowired
    BankAccountSpringDataRepository bankAccountSpringDataRepository;

    private Faker fs = new Faker();

    @Test(testName = "Validate Amount Transfer Between Accounts")
    public void validateAmountTransferBetweenAccounts() {

        List<BankAccount> bankAccountList = bankAccountService.transfer(5000, 500, 1200);

        List<Integer> balances = List.of(5000 - 1200, 500 + 1200);
        List<Integer> balanceInRespectiveAccount = new ArrayList<>();

        bankAccountList.forEach(bank -> balanceInRespectiveAccount.add(bank.getBal()));

        Arrays.sort(new List[]{balances});
        Arrays.sort(new List[]{balanceInRespectiveAccount});

        Assert.assertEquals(balances, balanceInRespectiveAccount);

    }

    @Test(testName = "Validate Rollback When Balance Is Not Available", expectedExceptions = RuntimeException.class)
    public void validateRollbackWhenBalanceIsNotAvailable() {

        bankAccountService.transfer(5000, 500, 5200);

    }

    @Test(testName = "Validate Rollnacl on Failed Transactions")
    public void validateRollbackOnFailedTransactions() {

        BankAccount bankAccountOne = null;
        BankAccount bankAccountTwo = null;

        int bankAccountOneInitialBalance = 500;
        int bankAccountTwoInitialBalance = 100;

        try {
            bankAccountOne = new BankAccount(fs.name().firstName(), fs.name().lastName(), bankAccountOneInitialBalance);
            bankAccountTwo = new BankAccount(fs.name().firstName(), fs.name().lastName(), bankAccountTwoInitialBalance);

            bankAccountService.transferWithException(bankAccountOne, bankAccountTwo, 100);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            Optional<BankAccount> bankAccountOneLatest = bankAccountSpringDataRepository.findById(bankAccountOne.getId());
            Optional<BankAccount> bankAccountTwoLatest = bankAccountSpringDataRepository.findById(bankAccountTwo.getId());

            Assert.assertTrue(bankAccountOneLatest.isEmpty());
            Assert.assertTrue(bankAccountOneLatest.isEmpty());
        }

    }

    @AfterMethod
    public void clearDB() {

        bankAccountSpringDataRepository.deleteAll();

    }
}
