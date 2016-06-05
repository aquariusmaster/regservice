package com.aquariusmaster.controller;

import com.aquariusmaster.entity.Account;
import com.aquariusmaster.service.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
            model.addAttribute("error", true);
            return "registration";
        }
        if(accountsService.exists(account.getEmail())) {
            result.rejectValue("email", "Email already exist", "Email already exists");
            model.addAttribute("error", true);
            return "registration";
        }
        if (accountsService.create(account)){
            return "success";
        }else{
            model.addAttribute("error", true);
        }
        return "registration";
    }

    @RequestMapping("/registration")
    public String showRegist(Model model){

        model.addAttribute("account", new Account());
        model.addAttribute("error", false);
        return "registration";
    }


}
