package com.aquariusmaster.service.messaging.mail.domain;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * Created by harkonnen on 05.06.16.
 */
public class MailUtils {

    public static String buildHTMLEmail(String email, String password){

        String encodedEmailAndCode = "";
        try
        {
            String emailAndCode = email + "@" + password;
            encodedEmailAndCode = Base64.getEncoder().encodeToString(emailAndCode.getBytes("UTF-8"));
        }
        catch (UnsupportedEncodingException e)
        {
        }

        StringBuilder body = new StringBuilder();

        body.append("<h2 align=\"center\">Thank you for registration!</h2>");
        body.append("<img src=\"http://print-convert.ru/rules_01.jpg\"  align=\"center\"/>");
        body.append("<h3>You have succesfully created account on our site.</h3>");
        body.append("<h3>Your email is " + email + " and you pass ends on *****" + password.substring(password.length()-2) + ".</h3>");
        body.append("<br>");
        body.append("<h3>To confirm your account please click on this link ");
        body.append("<a href=\"http:localhost:8080/confirm/" + encodedEmailAndCode + "\">http:localhost:8080/confirm/" + encodedEmailAndCode + "</a>.</h3>");
        return body.toString();
    }
}
