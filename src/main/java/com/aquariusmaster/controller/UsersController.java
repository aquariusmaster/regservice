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

    @RequestMapping("/")
    public String showHome(){

        return "redirect:/registration";
    }


    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(Model model, @Valid Account account, BindingResult result){

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
        return "success";
    }

    @RequestMapping("/registration")
    public String showRegist(Model model){

        model.addAttribute("account", new Account());
        model.addAttribute("return_status", false);
        return "registration";
    }


}
