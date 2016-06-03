package com.aquariusmaster.service;

import com.aquariusmaster.dao.AccountsDao;
import com.aquariusmaster.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service layer for Account operatins
 * @author Andrey Bobrov
 */
@Service("accountsService")
public class AccountsServiceImpl implements AccountsService {

    @Autowired
    AccountsDao accountsDao;

    @Override
    public boolean create(Account account) {

        return accountsDao.create(account);
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
