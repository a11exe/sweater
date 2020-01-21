package com.alllexe.sweater.controller;

import com.alllexe.sweater.domen.User;
import com.alllexe.sweater.domen.dto.CaptchaResponseDto;
import com.alllexe.sweater.service.UserService;
import java.util.Collections;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 06.12.2019
 */
@Controller
public class RegistrationController {

  private final static String CAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";

  @Autowired
  private UserService userService;

  @Autowired
  private RestTemplate restTemplate;

  @Value("${recaptcha.secret}")
  private String secret;

  @GetMapping("registration")
  public String registration() {
    return "registration";
  }

  @PostMapping("registration")
  public String addUser(
      @RequestParam("password2") String passwordConfirm,
      @RequestParam("g-recaptcha-response") String captchaResponse,
      @Valid User user,
      BindingResult bindingResult,
      Model model) {

    String url = String.format(CAPTCHA_URL, secret, captchaResponse);

    CaptchaResponseDto response = restTemplate
        .postForObject(url, Collections.emptyList(), CaptchaResponseDto.class);

    if (!response.isSuccess()) {
      model.addAttribute("captchaError", "Fill captcha");
    }

    boolean isConfirmEmpty = StringUtils.isEmpty(passwordConfirm);
    if (isConfirmEmpty) {
      model.addAttribute("password2Error", "Password confirm is empty");
    }
    if (user.getPassword() != null && !user.getPassword().equals(passwordConfirm)) {
      model.addAttribute("passwordError", "Passwords are different");
    }
    if (isConfirmEmpty || bindingResult.hasErrors() || !response.isSuccess()) {
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
      model.addAttribute("messageType", "success");
      model.addAttribute("message", "User successfully activated");
    } else {
      model.addAttribute("messageType", "danger");
      model.addAttribute("message", "Activation code is not found");
    }
    return "login";
  }

}
