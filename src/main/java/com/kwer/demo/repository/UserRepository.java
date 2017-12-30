package com.kwer.demo.repository;

import com.kwer.demo.pojo.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

  User findUserByUsername(String username);
}
