package com.aquariusmaster.service;

import com.aquariusmaster.dao.AccountsDao;
import com.aquariusmaster.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * Service layer for Account operations
 * @author Andrey Bobrov
 */
@Service("accountsService")
public class AccountsServiceImpl implements AccountsService {

    @Autowired
    AccountsDao accountsDao;
    @Autowired
    MailService mailService;

    @Override
    public boolean create(Account account) {

        if (accountsDao.create(account)){
            mailService.sendConfirmationLink(account);
            return true;
        };
        return false;
    }

    @Override
    public Account getAccount(String email) {

        return accountsDao.getAccount(email);
    }

    @Override
    public boolean exists(String email) {

        return accountsDao.exists(email);
    }

}
