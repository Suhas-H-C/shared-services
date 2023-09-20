package com.shared.info.service;

import com.shared.info.dto.Mail;

import javax.mail.MessagingException;

public interface EmailService {

    void sendEmailWithoutAttachment(Mail mail);
    void sendEmailWithAttachment(Mail mail) throws MessagingException;
}
