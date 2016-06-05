package com.aquariusmaster.dao;

import com.aquariusmaster.entity.Account;

/**
 * Created by harkonnen on 31.05.16.
 */
public interface AccountsDao {

    boolean create(Account account);
    Account getAccount(String email);
    boolean exists(String email);
    boolean update(Account account);

}
