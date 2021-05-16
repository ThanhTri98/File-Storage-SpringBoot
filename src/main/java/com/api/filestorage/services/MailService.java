package com.api.filestorage.services;

import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    @Autowired
    private JavaMailSender mailSender;

    private Random random = new Random();

    int sendEmail(String email) {
        try {
            int randomCode = random.nextInt(999999 - 100000) + 100000;
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setTo(email);
            helper.setSubject("[CODE] FILE STORAGE");
            helper.setText("Mã xác nhận của bạn là: <b>" + randomCode + "</b>", true);
            mailSender.send(message);
            return randomCode;
        } catch (MessagingException e) {
            e.printStackTrace();
            return 0;
        }
    }

}
