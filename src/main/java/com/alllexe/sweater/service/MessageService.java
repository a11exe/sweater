package com.alllexe.sweater.service;

import com.alllexe.sweater.domain.User;
import com.alllexe.sweater.domain.dto.MessageDto;
import com.alllexe.sweater.repository.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 05.02.2020
 */
@Service
public class MessageService {
  @Autowired
  private MessageRepo messageRepo;

  public Page<MessageDto> messageList(Pageable pageable, String filter, User user) {
    if (filter != null && !filter.isEmpty()) {
      return messageRepo.findByTag(filter, pageable, user);
    } else {
      return messageRepo.findAll(pageable, user);
    }
  }

  public Page<MessageDto> messageListForUser(Pageable pageable, User currentUser, User author) {
    return messageRepo.findByUser(pageable, author, currentUser);
  }
}
