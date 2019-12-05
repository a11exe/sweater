package com.alllexe.sweater.repository;

import com.alllexe.sweater.domen.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepo extends CrudRepository<Message, Integer> {

    Iterable<Message> findByTag(String tag);
}