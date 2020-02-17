package com.alllexe.sweater.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 14.01.2020
 */
@Service
public class MailSender {

  @Autowired
  private JavaMailSender mailSender;

  @Value("${spring.mail.username}")
  private String username;

  private static final Logger LOGGER = LoggerFactory.getLogger(MailSender.class);

  public void send(String mailTo, String subject, String message) {

    try {
      MimeMessage mimeMessage = mailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

      helper.setFrom(username);
      helper.setTo(mailTo);
      helper.setSubject(subject);
      helper.setText(message, true);

      mailSender.send(mimeMessage);
    } catch (MessagingException e) {
      LOGGER.error("error sending confirmation email {}", username);
    }
  }

}
