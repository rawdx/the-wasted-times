package com.newspaper.services.impl;

import com.newspaper.services.EmailService;
import com.newspaper.utils.EmailUtils;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * Implementation of the {@link EmailService} interface for sending emails.
 */
@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    private final JavaMailSender javaMailSender;

    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public boolean sendVerificationEmail(String email, String token) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            String link = "http://localhost:8080/verify/" + token;

            helper.setTo(email);
            helper.setSubject("Verify Your Email");

            String htmlContent = EmailUtils.generateVerificationEmailHtml(link);

            helper.setText(htmlContent, true);
            helper.addInline("mail.jpeg", new ClassPathResource("static/img/mail.jpeg"), "image/jpeg");

            javaMailSender.send(message);

            logger.info("Verification email sent to: {}", email);
            return true;
        } catch (Exception e) {
            logger.error("Error sending verification email to: {}", email, e);
            return false;
        }
    }

    @Override
    public boolean sendPasswordChangeEmail(String email, String token) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            String link = "http://localhost:8080/change-password/" + token;

            helper.setTo(email);
            helper.setSubject("Change Your Password");

            String htmlContent = EmailUtils.generatePasswordChangeEmailHtml(link);

            helper.setText(htmlContent, true);
            helper.addInline("lock.png", new ClassPathResource("static/img/lock.png"));

            javaMailSender.send(message);

            logger.info("Password change email sent to: {}", email);
            return true;
        } catch (Exception e) {
            logger.error("Error sending password change email to: {}", email);
            return false;
        }
    }
}
