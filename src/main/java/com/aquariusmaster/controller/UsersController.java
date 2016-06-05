package com.aquariusmaster.controller;

import com.aquariusmaster.entity.Account;
import com.aquariusmaster.service.AccountsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * Created by harkonnen on 31.05.16.
 */
@Controller
public class UsersController {

    @Autowired
    AccountsService accountsService;

    private static Logger logger = Logger.getLogger(UsersController.class);

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
            return "reg-success";
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

    @RequestMapping(value = "/confirm/{encodedEmailAndCode}", method = RequestMethod.GET)
    String confirmEmail(@PathVariable String encodedEmailAndCode) {
        String emailAndCode;
        try {
            emailAndCode = new String(Base64.getDecoder().decode(encodedEmailAndCode), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "error"; // TODO: what to return in this case?
        }
        String password = emailAndCode.split("@")[2];
        String email = emailAndCode.split("@" + password)[0];
        logger.info("Email to confirm: " + email);
        try {
            if (accountsService.confirmEmail(email)){
                return "redirect:/success";
            };
        } catch (Exception e) {
            // TODO: what to return in this case?
            return "error";
        }

        return "error";
    }

    @RequestMapping("/success")
    public String showConfirmed(){
        return "success";
    }


}
