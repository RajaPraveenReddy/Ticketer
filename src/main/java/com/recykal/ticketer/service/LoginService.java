package com.recykal.ticketer.service;

import com.recykal.ticketer.Dto.LoginDTO;
import com.recykal.ticketer.Exception.InvalidCredentialsException;
import com.recykal.ticketer.entity.Users;
import com.recykal.ticketer.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
public class LoginService {

    @Autowired
    private UsersRepository loginRepository;

    public UserResponseDTO loginUser(LoginDTO loginDTO) throws InvalidCredentialsException {
        String hashedPassword = hashPassword(loginDTO.getPassword());
        Users user = loginRepository.findByEmailAndPasswordAndActions(loginDTO.getEmail(), hashedPassword);
        if (user == null) {
            throw new InvalidCredentialsException("Invalid email or password!");
        } else if (!user.isActions()) {
            throw new InvalidCredentialsException("This user is not authorized to log in!");
        }
        return new UserResponseDTO(user.getId(), user.getFullName(), user.getEmail(), user.getRole(), user.isActions());
    }

    public String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedPasswordBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hashedPasswordBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static class UserResponseDTO {
        private long id;
        private String name;
        private String email;
        private String role;
        private boolean actions;

        public UserResponseDTO(long id, String name, String email, String role, boolean actions) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.role = role;
            this.actions = actions;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public boolean isActions() {
            return actions;
        }

        public void setActions(boolean actions) {
            this.actions = actions;
        }
    }
}
