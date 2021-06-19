package com.example.bankspring;

import com.example.bankspring.entity.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.example.bankspring.entity")
@EnableJpaRepositories
public class BankSpringApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(BankSpringApplication.class, args);
//        Bank bank = context.getBean(Bank.class);
////        Bank bank =
//        bank.getAccounts().stream().forEach(System.out::println);
    }

}
