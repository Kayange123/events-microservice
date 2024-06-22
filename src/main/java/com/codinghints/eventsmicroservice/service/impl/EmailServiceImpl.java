package com.codinghints.eventsmicroservice.service.impl;

import com.codinghints.eventsmicroservice.exception.ApiException;
import com.codinghints.eventsmicroservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import static com.codinghints.eventsmicroservice.utils.EmailUtils.getEmailMessage;
import static com.codinghints.eventsmicroservice.utils.EmailUtils.getPasswordResetMessage;

@Service
@RequiredArgsConstructor @Slf4j
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender javaMailSender;
    private final String NEW_ACCOUNT_EMAIL_SUBJECT = "New Use Account Verification";
    private final String PASSWORD_RESET_EMAIL_SUBJECT = "Password Reset Request";

    @Value("${spring.mail.verify.host}")
    private String host;
    @Value("${spring.mail.username}")
    private String fromEmail;

    @Override
    @Async
    public void sendNewAccountEmail(String name, String to, String token) {
        try{
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setSubject(NEW_ACCOUNT_EMAIL_SUBJECT);
            mailMessage.setFrom(fromEmail);
            mailMessage.setTo(to);
            mailMessage.setText(getEmailMessage(name, host, token));
            javaMailSender.send(mailMessage);
        }catch (Exception ex){
            log.info(ex.getMessage());
            throw new ApiException("Unable to send an Email");
        }
    }

    @Override
    @Async
    public void sendPasswordResetEmail(String name, String to, String token) {
        try{
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setSubject(NEW_ACCOUNT_EMAIL_SUBJECT);
            mailMessage.setFrom(fromEmail);
            mailMessage.setTo(to);
            mailMessage.setText(getPasswordResetMessage(name, host, token));
            javaMailSender.send(mailMessage);
        }catch (Exception ex){
            log.info(ex.getMessage());
            throw new ApiException("Unable to send an Email");
        }
    }
}
