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
public class RegserviceApplication {


	public static void main(String[] args) {

        SpringApplication.run(RegserviceApplication.class, args);

    }

}
