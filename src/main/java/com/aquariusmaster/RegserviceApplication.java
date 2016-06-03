package com.aquariusmaster;

import com.aquariusmaster.entity.Account;
import com.aquariusmaster.service.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@ComponentScan("com.aquariusmaster")
public class RegserviceApplication {

    @Autowired
    AccountsService accountsService;

	public static void main(String[] args) {

        SpringApplication.run(RegserviceApplication.class, args);

    }

    public void run(String... args) {
        Account account = new Account();
        account.setEmail("Aquariusmaster@yandex.ru");
        account.setPassword("!hello12");
        account.setIs_confirmed(true);

        boolean created = accountsService.create(account);
        System.out.println("Account created: " + created);

        Account retrived = accountsService.getAccount("Aquariusmaster@yandex.ru");
        System.out.println("Account retrived:");
        System.out.println("\t" + retrived);
    }


}
