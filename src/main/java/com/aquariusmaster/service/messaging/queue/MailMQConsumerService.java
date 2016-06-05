package com.aquariusmaster.service.messaging.queue;

import com.aquariusmaster.service.messaging.mail.MailSenderServiceImp;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import com.aquariusmaster.service.messaging.mail.domain.MailMessage;
import org.springframework.stereotype.Component;

/**
 * Created by harkonnen on 05.06.16.
 */
@Component
public class MailMQConsumerService {

    private static Logger logger = Logger.getLogger(MailMQConsumerService.class);

    @Autowired
    MailSenderServiceImp mailService;

    @JmsListener(destination = "MainQueue", containerFactory = "myJmsContainerFactory")
    public void receiveMessage(MailMessage mailMessage) {
        logger.info("Message received: " + mailMessage);
        mailService.sendMail(mailMessage);
    }
}
