package com.alllexe.sweater.config;

import java.util.Properties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 14.01.2020
 */
@Configuration
public class MailConfig {

  @Value("${spring.mail.host}")
  private String host;

  @Value("${spring.mail.username}")
  private String username;

  @Value("${spring.mail.password}")
  private String password;

  @Value("${spring.mail.port}")
  private int port;

  @Value("${spring.mail.protocol}")
  private String protocol;

  @Value("${mail.debug}")
  private String debug;

  @Bean
  public JavaMailSender getMailSender() {
    JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
    javaMailSender.setHost(host);
    javaMailSender.setPort(port);
    javaMailSender.setUsername(username);

    Properties properties = javaMailSender.getJavaMailProperties();
    properties.setProperty("mail.transport.protocol", protocol);
    properties.setProperty("mail.debug", debug);

    return javaMailSender;
  }
}
