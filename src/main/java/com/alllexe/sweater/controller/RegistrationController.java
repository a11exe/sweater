package com.alllexe.sweater.controller;

import com.alllexe.sweater.domen.Role;
import com.alllexe.sweater.domen.User;
import com.alllexe.sweater.repository.UserRepo;
import java.util.Collections;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 06.12.2019
 */
@Controller
public class RegistrationController {

  @Autowired
  private UserRepo userRepo;

  @GetMapping("registration")
  public String registration() {
    return "registration";
  }

  @PostMapping("registration")
  public String addUser(User user, Map<String, Object> model) {

    User userFromDb = userRepo.findByUsername(user.getUsername());

    if (userFromDb != null) {
      model.put("message", "user exists!");
      return "registration";
    }
    user.setActive(true);
    user.setRoles(Collections.singleton(Role.USER));

    userRepo.save(user);
    return "redirect:/login";
  }

}
