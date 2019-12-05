package com.alllexe.sweater.controller;

import com.alllexe.sweater.domen.Message;
import com.alllexe.sweater.repository.MessageRepo;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 05.12.2019
 */
@Controller
public class GreetingController {

  @Autowired
  private MessageRepo messageRepo;

  @GetMapping("/greeting")
  public String greeting(
      @RequestParam(name = "name", required = false, defaultValue = "World") String name,
      Map<String, Object> model) {
    model.put("name", name);
    return "greeting";
  }

  @GetMapping
  public String main(Map<String, Object> model) {
    Iterable<Message> messages = messageRepo.findAll();
    model.put("messages", messages);
    return "main";
  }

  @PostMapping
  public String add(@RequestParam String text, @RequestParam String tag, Map<String, Object> model) {
    messageRepo.save(new Message(text, tag));
    Iterable<Message> messages = messageRepo.findAll();
    model.put("messages", messages);
    return "main";
  }

  @PostMapping(path="/filter")
  public String find(@RequestParam String filter, Map<String, Object> model) {
    Iterable<Message> messages;
    if (filter == null || filter.isEmpty()) {
      messages = messageRepo.findAll();
    } else {
      messages = messageRepo.findByTag(filter);
    }
    model.put("messages", messages);
    return "main";
  }

}
