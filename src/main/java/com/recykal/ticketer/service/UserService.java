package com.recykal.ticketer.service;

import com.recykal.ticketer.Dto.SignUpDTO;
import com.recykal.ticketer.Exception.EmailAlreadyExistsException;
import com.recykal.ticketer.Exception.ResourceNotFoundException;
import com.recykal.ticketer.entity.Users;
import com.recykal.ticketer.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private LoginService loginService;

public void enableUser(long id) {
    Optional<Users> optionalUser = usersRepository.findUsersById(id);
    if (optionalUser.isPresent()) {
        Users user = optionalUser.get();
        user.setActions(true);
        usersRepository.save(user);
    }
}

    public void disableUser(long id) {
        Optional<Users> optionalUser = usersRepository.findUsersById(id);
        if (optionalUser.isPresent()) {
            Users user = optionalUser.get();
            user.setActions(false);
            usersRepository.save(user);
        }
    }

    public List<Users> getUsersByDept(String dept) {
        String trimmedDept = dept.trim();
        return usersRepository.findByDeptContaining(trimmedDept);
    }

    public Optional<Users> findByUserId(long id) {
        return usersRepository.findById(id);
    }

    public Map<String, Long> getCountByActions() {
        return usersRepository.getCountByActions();
    }

    public List<Users> getAllUsers(){
        return usersRepository.findAll();
    }

    public Users getUsersById(long id) throws ResourceNotFoundException {
        return usersRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id : " + id));
    }

    public Users registerNewUser(SignUpDTO signUpDTO) throws EmailAlreadyExistsException {
        if (!validateSignUpDTO(signUpDTO)) {
            throw new IllegalArgumentException("Invalid signUpDTO object");
        }
        String hashedPassword = loginService.hashPassword(signUpDTO.getPassword());
        signUpDTO.setPassword(hashedPassword);
        if (usersRepository.findByEmail(signUpDTO.getEmail()) != null) {
            throw new EmailAlreadyExistsException("Email already exists proceed to login");
        }
            Users user = new Users();
            user.setFullname(signUpDTO.getFullName());
            user.setDept(signUpDTO.getDept());
            user.setEmail(signUpDTO.getEmail());
            user.setPassword((signUpDTO.getPassword()));
            user.setDate(ZonedDateTime.now());
            user.setActions(true);
            user.setRole("user");
            return usersRepository.save(user);
        }

    public boolean validateSignUpDTO(SignUpDTO signUpDTO) {
        boolean valid = true;
        String fullName = signUpDTO.getFullName();
        String email = signUpDTO.getEmail();
        String password = signUpDTO.getPassword();

        if (fullName == null || fullName.trim().isEmpty() || fullName.length() > 50) {
            valid = false;
        }

        if (email == null || email.trim().isEmpty() || !email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            valid = false;
        }

        if (password == null || password.length() < 8 || password.length() > 50) {
            valid = false;
        }

        return valid;
    }

}

