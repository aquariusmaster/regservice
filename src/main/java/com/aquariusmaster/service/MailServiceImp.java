package com.aquariusmaster.service;

import com.aquariusmaster.entity.Account;
import com.aquariusmaster.service.messaging.mail.domain.MailMessage;
import com.aquariusmaster.service.messaging.mail.domain.MailUtils;
import com.aquariusmaster.service.messaging.queue.MailMQPublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Base64;

/**
 * Created by harkonnen on 05.06.16.
 */
@Service("mailService")
public class MailServiceImp implements MailService{

    @Autowired
    private MailMQPublisherService mailMQPublisherService;

    public void sendConfirmationLink( Account account ) {
        MailMessage message = new MailMessage();
        message.setSender("verydrybeaver@gmail.com");
        message.setReplyTo("verydrybeaver@gmail.com");
        message.setSubject("Confirm your registration");
        message.setToList(Arrays.asList(account.getEmail()));
        String encodedEmailAndCode;
        try
        {
            String emailAndCode = account.getEmail() + "@" + account.getPassword();
            encodedEmailAndCode = Base64.getEncoder().encodeToString(emailAndCode.getBytes("UTF-8"));
        }
        catch (UnsupportedEncodingException e)
        {
            return;
        }
        message.setBody(MailUtils.buildHTMLEmail(account.getEmail(), account.getPassword())); // TODO: unhardcode server address
        mailMQPublisherService.publishMailMessage(message);
    }
}
