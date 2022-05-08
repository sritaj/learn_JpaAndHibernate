package in.sritaj.jpaandhibernate.service.transactionmanagement;

import com.github.javafaker.Faker;
import in.sritaj.jpaandhibernate.entity.transactionmanagement.BankAccount;
import in.sritaj.jpaandhibernate.repository.transactionmanagement.BankAccountSpringDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    @Autowired
    private BankAccountSpringDataRepository bankAccountSpringDataRepository;

    private Faker fs = new Faker();

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
