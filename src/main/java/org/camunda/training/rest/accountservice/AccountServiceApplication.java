package org.camunda.training.rest.accountservice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class AccountServiceApplication {

    private AccountRepository accountRepo;

    public AccountServiceApplication(AccountRepository accountRepo) {
        this.accountRepo = accountRepo;
    }

    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        long accountNo = 1000000;
        accountRepo.save(new Account(accountNo++, "John", "Mayer", 1000));
        accountRepo.save(new Account(accountNo++, "Alicia", "Keys", 2000));
        accountRepo.save(new Account(accountNo++, "Tom", "Jones", 3000));
        accountRepo.save(new Account(accountNo, "Miriam", "Makeba", 4000));

    }
}
