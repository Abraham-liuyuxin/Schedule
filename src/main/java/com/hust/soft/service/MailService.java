package com.hust.soft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    String SERVER_PREFIX = "http://locahost:4000/register/verification?verification_code=";

    @Autowired
    private JavaMailSender mailSender;

    public String verificationLink(String toEmail, String body){
        String link = SERVER_PREFIX + body + "&email=" + toEmail;
        return link;
    }

    public void simpleMail(String toEmail, String body) {
        String fromEmail = "zhacaiji@163.com";
        String subject = "Verification";
        body = verificationLink(toEmail, body);

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom(fromEmail);
        mail.setTo(toEmail);
        mail.setSubject(subject);
        mail.setText(body);

        mailSender.send(mail);
    }
}
