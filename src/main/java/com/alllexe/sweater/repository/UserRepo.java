package com.alllexe.sweater.repository;

import com.alllexe.sweater.domen.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {

  User findByUsername(String username);

  User findByActivationCode(String code);
}
