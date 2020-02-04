package com.alllexe.sweater.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.ArgumentMatchers.eq;

import com.alllexe.sweater.domen.Role;
import com.alllexe.sweater.domen.User;
import com.alllexe.sweater.repository.UserRepo;
import java.util.Collections;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 04.02.2020
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

  @Autowired
  private UserService userService;

  @MockBean
  private UserRepo userRepo;

  @MockBean
  private PasswordEncoder passwordEncoder;

  @MockBean
  private MailSender mailSender;

  @Test
  public void addUser() {
    User user = new User();
    user.setEmail("some@mail.com");
    boolean isUserCreated = userService.addUser(user);
    assertTrue(isUserCreated);
    assertNotNull(user.getActivationCode());
    assertThat(user.getRoles(), is(Collections.singleton(Role.USER)));

    Mockito.verify(userRepo, Mockito.times(1)).save(user);
    Mockito.verify(mailSender, Mockito.times(1))
        .send(
            eq(user.getEmail()),
            eq("Activation code"),
            contains("Welcome to Sweater"));
  }

  @Test
  public void addUserFailed() {

    User user = new User();
    user.setUsername("John");

    Mockito.doReturn(new User())
        .when(userRepo)
        .findByUsername("John");

    boolean isUserCreated = userService.addUser(user);
    assertFalse(isUserCreated);

    Mockito.verify(userRepo, Mockito.times(0)).save(any(User.class));
    Mockito.verify(mailSender, Mockito.times(0)).send(
        anyString(),
        anyString(),
        anyString());

  }

  @Test
  public void activateUser() {

    User user = new User();
    user.setActivationCode("activate");
    Mockito.doReturn(user)
        .when(userRepo)
        .findByActivationCode("activate");

    boolean isUserActivated = userService.activateUser("activate");
    assertTrue(isUserActivated);
    assertNull(user.getActivationCode());
    Mockito.verify(userRepo, Mockito.times(1)).save(user);
  }

  @Test
  public void activateUserFailed() {

    User user = new User();
    user.setActivationCode("activate");
    Mockito.doReturn(null)
        .when(userRepo)
        .findByActivationCode("activate");

    boolean isUserActivated = userService.activateUser("activate");
    assertFalse(isUserActivated);
    assertNotNull(user.getActivationCode());
    Mockito.verify(userRepo, Mockito.times(0)).save(user);
  }
}