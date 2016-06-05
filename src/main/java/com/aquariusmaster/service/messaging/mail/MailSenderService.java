package com.aquariusmaster.service.messaging.mail;

import com.aquariusmaster.service.messaging.mail.domain.MailMessage;

/**
 * Created by harkonnen on 05.06.16.
 */
public interface MailSenderService {

    public void sendMail(MailMessage mailMessage);
}
