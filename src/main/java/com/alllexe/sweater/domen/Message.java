package com.alllexe.sweater.domen;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 05.12.2019
 */
@Entity
public class Message {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private long id;

  private String text;
  private String tag;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id")
  private User author;

  @Column(name = "file")
  private String filename;

  public Message() {
  }

  public Message(String text, String tag, User user) {
    this.author = user;
    this.text = text;
    this.tag = tag;
  }

  public String getAuthorName() {
    return getAuthor() == null ? "" : getAuthor().getUsername();
  }


  public User getAuthor() {
    return author;
  }

  public void setAuthor(User author) {
    this.author = author;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  public String getFilename() {
    return filename;
  }

  public void setFilename(String filename) {
    this.filename = filename;
  }
}
