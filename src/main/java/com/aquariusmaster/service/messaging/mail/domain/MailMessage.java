package com.aquariusmaster.service.messaging.mail.domain;

/**
 * Created by harkonnen on 05.06.16.
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MailMessage implements Serializable {


    private static final long serialVersionUID = 6713282195933558600L;
    private String sender;
    private String replyTo;
    private List<String> toList = new ArrayList<>();
    private List<String> ccList = new ArrayList<>();
    private List<String> bccList = new ArrayList<>();
    private String subject;
    private String body;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(String replyTo) {
        this.replyTo = replyTo;
    }

    public List<String> getToList() {
        return toList;
    }

    public void setToList(List<String> toList) {
        this.toList = toList;
    }

    public List<String> getCcList() {
        return ccList;
    }

    public void setCcList(List<String> ccList) {
        this.ccList = ccList;
    }

    public List<String> getBccList() {
        return bccList;
    }

    public void setBccList(List<String> bccList) {
        this.bccList = bccList;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "MailMessage{" +
                "sender='" + sender + '\'' +
                ", replyTo='" + replyTo + '\'' +
                ", toList=" + toList +
                ", ccList=" + ccList +
                ", bccList=" + bccList +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}