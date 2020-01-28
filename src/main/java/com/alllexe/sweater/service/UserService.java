package com.alllexe.sweater.service;

import com.alllexe.sweater.domen.Role;
import com.alllexe.sweater.domen.User;
import com.alllexe.sweater.repository.UserRepo;
import freemarker.template.utility.StringUtil;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 06.12.2019
 */
@Service
public class UserService implements UserDetailsService {

  @Autowired
  private UserRepo userRepo;

  @Autowired
  private MailSender mailSender;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepo.findByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("Bad credentionals");
    }
    return user;
  }

  public boolean addUser(User user) {
    User userFromDb = userRepo.findByUsername(user.getUsername());
    if (userFromDb != null) {
      return false;
    }
    user.setActive(true);
    user.setRoles(Collections.singleton(Role.USER));
    user.setActivationCode(UUID.randomUUID().toString());
    user.setPassword(passwordEncoder.encode(user.getPassword()));

    sendMessage(user);

    userRepo.save(user);

    return true;
  }

  private void sendMessage(User user) {
    if (!StringUtils.isEmpty(user.getEmail())) {
      String message = String.format(
        "Hello %s!, \n" +
            "Welcome to Sweater! \n" +
            "To activate account visit http://localhost:8080/activate/%s", user.getUsername(), user.getActivationCode()
      );
      mailSender.send(user.getEmail(), "Activation code", message);
    }
  }

  public boolean activateUser(String code) {
    User user = userRepo.findByActivationCode(code);
    if (user == null) {
      return false;
    }
    user.setActivationCode(null);
    user.setActive(true);
    userRepo.save(user);
    return true;
  }

  public List<User> findAll() {
    return userRepo.findAll();
  }

  public void saveUser(User user, String username, Map<String, String> form) {
    user.setUsername(username);
    Set<String> roles =
        Arrays.stream(Role.values())
            .map(Role::name)
            .collect(Collectors.toSet());

    user.getRoles().clear();

    for (String key : form.keySet()) {
      if (roles.contains(key)) {
        user.getRoles().add(Role.valueOf(key));
      }
    }
    userRepo.save(user);
  }

  public void updateProfile(User user, String password, String email) {
    String userEmail = user.getEmail();
    boolean isEmailChanged = (email != null) && (!email.equals(userEmail)) ||
        (userEmail != null) && (!userEmail.equals(email));

    if (isEmailChanged) {
      user.setEmail(email);
      if (!StringUtils.isEmpty(email)) {
        user.setActivationCode(UUID.randomUUID().toString());
      }
    }

    if (!StringUtils.isEmpty(password)) {
      user.setPassword(password);
    }

    userRepo.save(user);
    if (isEmailChanged) {
      sendMessage(user);
    }
  }

  public void subscribe(User currentUser, User user) {
    user.getSubscribers().add(currentUser);
    userRepo.save(user);
  }

  public void unsubscribe(User currentUser, User user) {
    user.getSubscribers().remove(currentUser);
    userRepo.save(user);
  }
}
