package com.alllexe.sweater.repository;

import com.alllexe.sweater.domain.User;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {

  User findByUsername(String username);

  User findByActivationCode(String code);

  List<User> findAll();

}
