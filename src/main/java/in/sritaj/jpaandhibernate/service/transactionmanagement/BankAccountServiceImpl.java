package in.sritaj.jpaandhibernate.service.transactionmanagement;

import com.github.javafaker.Faker;
import in.sritaj.jpaandhibernate.entity.transactionmanagement.BankAccount;
import in.sritaj.jpaandhibernate.repository.transactionmanagement.BankAccountSpringDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * BankAccountServiceImpl class extending BankAccountService interface for defining the Business Logics
 */
@Service
public class BankAccountServiceImpl implements BankAccountService {

    @Autowired
    private BankAccountSpringDataRepository bankAccountSpringDataRepository;

    private Faker fs = new Faker();

    /**
     * Method to transfer balance from One Account to Other Account
     *
     * @param accountOneBalance - balance for Account to be deducted
     * @param accountTwoBalance - balance for Account to be debited
     * @param amountToTransfer  - amount to be deducted
     * @return List - Details of deducted and debited account
     */
    @Override
    @Transactional
    public List<BankAccount> transfer(int accountOneBalance, int accountTwoBalance, int amountToTransfer) {

        BankAccount createdAccountOne = bankAccountSpringDataRepository.save(new BankAccount(fs.name().firstName(), fs.name().lastName(), accountOneBalance));
        BankAccount createdAccountTwo = bankAccountSpringDataRepository.save(new BankAccount(fs.name().firstName(), fs.name().lastName(), accountTwoBalance));

        if (amountToTransfer > accountOneBalance || accountOneBalance == 0) {
            throw new RuntimeException();
        }

        createdAccountOne.setBal(accountOneBalance - amountToTransfer);

        createdAccountTwo.setBal(accountTwoBalance + amountToTransfer);

        bankAccountSpringDataRepository.save(createdAccountOne);
        bankAccountSpringDataRepository.save(createdAccountTwo);

        return List.of(createdAccountOne, createdAccountTwo);

    }

    /**
     * Method to transfer balance from One Account to Other Account with Exception to halt the execution in between
     *
     * @param accountOne       - Account from which amount to be deducted
     * @param accountTwo       - Account for which amount to be debited
     * @param amountToTransfer - amount to be deducted
     */
    @Override
    @Transactional
    public void transferWithException(BankAccount accountOne, BankAccount accountTwo, int amountToTransfer) {

        bankAccountSpringDataRepository.save(accountOne);
        bankAccountSpringDataRepository.save(accountTwo);

        BankAccount createdAccountOne = bankAccountSpringDataRepository.save(accountOne);
        BankAccount createdAccountTwo = bankAccountSpringDataRepository.save(accountTwo);

        createdAccountOne.setBal(accountOne.getBal() - amountToTransfer);

        if (true)
            throw new RuntimeException();

        createdAccountTwo.setBal(accountTwo.getBal() + amountToTransfer);

        bankAccountSpringDataRepository.save(createdAccountOne);
        bankAccountSpringDataRepository.save(createdAccountTwo);

    }
}
