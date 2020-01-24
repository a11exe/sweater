package com.alllexe.sweater.repository;

import com.alllexe.sweater.domen.Message;
import com.alllexe.sweater.domen.User;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepo extends CrudRepository<Message, Long> {

    Iterable<Message> findByTag(String tag);

}