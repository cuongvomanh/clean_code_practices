package com.example.bankspring.BankAOP;

import com.example.bankspring.entity.Account;
import com.example.bankspring.entity.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class BankController {
    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private AccountRepository accountRepository;

    @PostConstruct
    public void getBankAndAccount(){
        System.out.println("Hello Worlds");
        Bank bank = new Bank();
        bank.setId(1L);
        bank.setName("Tech");
        bankRepository.save(bank);

//        Account a1 = new Account(){{setId(1L);setBank(bank);setName("cuongvm12");setLocation("Ba Trieu");}};
//        Account a2 = new Account(){{setId(2L);setBank(bank);setName("cuongvomanh");setLocation("Tran Duy Hung");}};
//        accountRepository.save(a1);
//        accountRepository.save(a2);


        bankRepository.findById(1L).ifPresent(e -> System.out.println("Bank: " + e.getName()));
    }

}
