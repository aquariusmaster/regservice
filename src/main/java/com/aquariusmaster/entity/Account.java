package com.aquariusmaster.entity;

import org.hibernate.validator.constraints.Email;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Pattern;

/**
 * Created by harkonnen on 31.05.16.
 * POJO class stored Account informations
 * @author Andrey Bobrov
 */
@Component
public class Account {

    /** Email for account.*/
    @Email
    private String email;

    /** Password field for account.*/
    @Pattern(regexp = "(?=(.*\\d){2})(?=.*[!]).*", message = "Password must contain at least 2 digits and one '!' symbol")
    private String password;

    /** Label check is_account confirmed through  email */
    private boolean is_confirmed = false;

    /**
     * Default constructor
     */
    public Account() {
    }

    /**
     * Create new Account with given email and password
     * @param email Email
     * @param password Password
     */
    public Account(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean is_confirmed() {
        return is_confirmed;
    }

    public void setIs_confirmed(boolean is_confirmed) {
        this.is_confirmed = is_confirmed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        return email.equals(account.email.toLowerCase());

    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }

    @Override
    public String toString() {
        return "Account{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", is_confirmed=" + is_confirmed +
                '}';
    }
}
