package com.aquariusmaster.service;

import com.aquariusmaster.entity.Account;

/**
 * Created by harkonnen on 05.06.16.
 */
public interface MailService {
    void sendConfirmationLink(Account account);
}
