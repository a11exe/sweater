package com.alllexe.sweater.controller;

import com.alllexe.sweater.domen.User;
import com.alllexe.sweater.service.UserService;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 06.12.2019
 */
@Controller
public class RegistrationController {

  @Autowired
  private UserService userService;

  @GetMapping("registration")
  public String registration() {
    return "registration";
  }

  @PostMapping("registration")
  public String addUser(@Valid User user,
      BindingResult bindingResult,
      Model model) {

    if (user.getPassword() != null && !user.getPassword().equals(user.getPassword2())) {
      model.addAttribute("passwordError", "Passwords are different");
    }
    if (bindingResult.hasErrors()) {
      Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
      model.mergeAttributes(errorsMap);
      return "registration";
    }
    if (!userService.addUser(user)) {
      model.addAttribute("userNameError", "user exists!");
      return "registration";
    }


    return "redirect:/login";
  }

  @GetMapping("/activate/{code}")
  public String activate(Model model, @PathVariable String code) {

    boolean isActivated = userService.activateUser(code);
    if (isActivated) {
      model.addAttribute("message", "User successfully activated");
    } else {
      model.addAttribute("message", "Activation code is not found");
    }
    return "login";
  }

}
