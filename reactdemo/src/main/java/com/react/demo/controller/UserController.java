package com.react.demo.controller;

import com.react.demo.domain.User;
import com.react.demo.repostitory.UserRepository;
import com.react.demo.service.dto.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by bishal on 6/20/17.
 */

@CrossOrigin
@RestController
public class UserController {

    private UserRepository userRepository;

    UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/user")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO){
        System.out.println(userDTO.toString());
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        userRepository.save(user);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<?> getAllUser(){
        List<User> userList = userRepository.findAll();
        return new ResponseEntity<List<User>>(userList,HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> findUser(@PathVariable Long id){
        User user = userRepository.findOne(id);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<?> editUser(@PathVariable Long id, @RequestBody UserDTO userDTO){
        System.out.println("*******************************8");
        System.out.println(userDTO.toString());
        System.out.println(id);
        User user = userRepository.findOne(id);
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        userRepository.save(user);
        return new ResponseEntity<String>(userDTO.toString(),HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        User user = userRepository.findOne(id);
        userRepository.delete(id);
        return new ResponseEntity<List<User>>(userRepository.findAll(),HttpStatus.OK);
    }
}
