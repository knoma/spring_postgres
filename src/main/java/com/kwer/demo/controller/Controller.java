package com.kwer.demo.controller;


import com.kwer.demo.pojo.User;
import com.kwer.demo.repository.UserRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class Controller {

    final UserRepository userRepository;

    @Inject
    public Controller(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public ModelAndView home() {
        return new ModelAndView("index");
    }

    @GetMapping(value = "/hello")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok().body("Hello there !");
    }

    @PostMapping(value = "/user/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> create(@PathVariable String username) {
        User user = new User();
        user.setUsername(username);
        User saved = userRepository.save(user);
        return ResponseEntity.ok().body(saved);
    }

    @GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> get(@PathVariable Long id) {
        User saved = userRepository.findOne(id);
        return ResponseEntity.ok().body(saved);
    }

    @GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> findAll() {
        final List<User> resultList = new ArrayList<>();
        final Iterable<User> all = userRepository.findAll();
        all.forEach(resultList::add);
        return ResponseEntity.ok().body(resultList);
    }

}