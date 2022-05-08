package in.sritaj.jpaandhibernate.service.transactionmanagement;

import in.sritaj.jpaandhibernate.entity.transactionmanagement.BankAccount;

import java.util.List;

public interface BankAccountService {

    List<BankAccount> transfer(int accountOneBalance, int accountTwoBalance, int amountToTransfer);

    void transferWithException(BankAccount accountOne, BankAccount accountTwo, int amountToTransfer);

}
