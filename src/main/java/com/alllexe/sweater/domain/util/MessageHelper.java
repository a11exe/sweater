package com.alllexe.sweater.domain.util;

import com.alllexe.sweater.domain.User;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 05.02.2020
 */
public abstract class MessageHelper {
  public static String getAuthorName(User author) {
    return author != null ? author.getUsername() : "<none>";
  }

}
