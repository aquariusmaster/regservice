package com.aquariusmaster.service;

import com.aquariusmaster.entity.Account;

/**
 * Created by harkonnen on 31.05.16.
 */
public interface AccountsService {

    boolean create(Account account);
    Account getAccount(String email);
    boolean exists(String email);

}
