package com.recykal.ticketer.controller;

import com.recykal.ticketer.Dto.LoginDTO;
import com.recykal.ticketer.Exception.InvalidCredentialsException;
import com.recykal.ticketer.entity.Users;
import com.recykal.ticketer.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private  LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO) throws InvalidCredentialsException {
        LoginService.UserResponseDTO user = loginService.loginUser(loginDTO);
//      return new ResponseEntity<>("Login successful for user with email: " + user.getEmail(), HttpStatus.OK);
        return ResponseEntity.ok(user);
    }
}
