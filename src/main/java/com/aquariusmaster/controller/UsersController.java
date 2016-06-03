package com.aquariusmaster.controller;

import com.aquariusmaster.entity.Account;
import com.aquariusmaster.service.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.validation.Valid;

/**
 * Created by harkonnen on 31.05.16.
 */
@Controller
public class UsersController {

    @Autowired
    AccountsService accountsService;

    @ModelAttribute("account")
    public Account constructAccount() {
        return new Account();
    }

    @RequestMapping("/home")
    public String showHome(){

        return "home";
    }

    @RequestMapping("/test")
    public String test(){

        Account account = new Account();
        account.setEmail("Aquariusmaster@yandex.ru");
        account.setPassword("hello12");
        account.setIs_confirmed(true);

        boolean created = accountsService.create(account);
        System.out.println("Account created: " + created);

        Account retrived = accountsService.getAccount("Aquariusmaster@yandex.ru");
        System.out.println("Account retrived:");
        System.out.println("\t" + retrived);
        return "home";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registarion(Model model, @Valid Account account, BindingResult result){

        if(result.hasErrors()) {
            model.addAttribute("return_status", true);
            return "registration";
        }
        if(accountsService.exists(account.getEmail())) {
            result.rejectValue("email", "Email already exist", "Email already exists");
            model.addAttribute("return_status", true);
            return "registration";
        }
        accountsService.create(account);
        System.out.println(account);
        return "registration";
    }

    @RequestMapping("/registration")
    public String showRegist(Model model){

        model.addAttribute("account", new Account());
        model.addAttribute("return_status", false);
        return "registration";
    }


}
