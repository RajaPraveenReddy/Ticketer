package com.recykal.ticketer.controller;

import com.recykal.ticketer.Dto.SignUpDTO;
import com.recykal.ticketer.Exception.EmailAlreadyExistsException;
import com.recykal.ticketer.entity.Users;
import com.recykal.ticketer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class UserController {
@Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDTO signUpDto) throws EmailAlreadyExistsException {
        userService.registerNewUser(signUpDto);
        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    }

    @PostMapping("/{id}/enable")
    public ResponseEntity<?> enableUser(@PathVariable long id) {
        userService.enableUser(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/disable")
    public ResponseEntity<?> disableUser(@PathVariable long id) {
        userService.disableUser(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/usersCount")
    public Map<String, Long> getCountByActions() {
        return userService.getCountByActions();
    }

    @GetMapping("/users")
    public List<Users> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/byDept/{dept}")
    public List<Users> getUsersByDept(@PathVariable String dept) {
        return userService.getUsersByDept(dept);
    }

}
