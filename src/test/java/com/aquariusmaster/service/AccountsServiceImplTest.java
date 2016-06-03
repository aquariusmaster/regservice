package com.aquariusmaster.service;

import com.aquariusmaster.RegserviceApplication;
import com.aquariusmaster.entity.Account;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
//static import org

/**
 * Created by harkonnen on 31.05.16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RegserviceApplication.class)
public class AccountsServiceImplTest extends TestCase {

    @Autowired
    AccountsService accountsService;


    @Test
    public void testCreate() throws Exception {

        Account account = getAccount();
        account.setEmail("verydrybeaver@gmail.com");
        assertEquals(true, accountsService.create(account));
        try{
            account.setEmail("Verydrybeaver@gmail.com");
            assertEquals(false, accountsService.create(account));
        }catch(Exception e){
            assertTrue(accountsService.exists("Verydrybeaver@gmail.com"));
        }


    }
    @Test
    public void testGetAccount() throws Exception {

        Account account = getAccount();
        account.setEmail("mail@gmail.com");
        assertTrue(accountsService.create(account));
        assertEquals(account, accountsService.getAccount("mail@gmail.com"));

    }
    @Test
    public void testExist() throws Exception {

        Account account = getAccount();
        assertTrue(accountsService.create(account));
        assertTrue(accountsService.exists("Aquariusmaster@yandex.ru"));
        assertTrue(accountsService.exists("aquariusmaster@yandex.ru"));

    }

    @Test
    public void testAccountValidation() throws Exception {

        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();

        Account account = getAccount();

        Set<ConstraintViolation<Account>> violations = validator.validate(account);
        System.out.println("Account is: " + account);
        System.out.println("Errors: " + violations);
        assertTrue(violations.isEmpty());

        account.setEmail("wrongEmail");
        violations = validator.validate(account);
        System.out.println("Errors: " + violations);
        assertEquals(1, violations.size());

        account.setPassword("544554564!");
        violations = validator.validate(account);
        System.out.println("Errors: " + violations);
        assertEquals(1, violations.size());

        account.setPassword("!5hggh");
        violations = validator.validate(account);
        System.out.println("Errors: " + violations);
        assertEquals(2, violations.size());

        account.setPassword("h5g!g0h");
        violations = validator.validate(account);
        System.out.println("Errors: " + violations);
        assertEquals(1, violations.size());


    }

    public Account getAccount(){
        Account account = new Account();
        account.setEmail("Aquariusmaster@yandex.ru");
        account.setPassword("!hello12");
        account.setIs_confirmed(true);
        return account;
    }
}