package com.recykal.ticketer.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SignUpDTO {

    @NotBlank(message = "Full name is required")
    @Size(max = 50, message = "Full name cannot be more than 50 characters")
    private String fullName;
    @NotBlank(message = "Department is required")
    @Size(max = 50, message = "Department cannot be more than 50 characters")
    private String dept;
    @NotBlank(message = "Email address is required")
    @Email(message = "Invalid email address")
    @Size(max = 100, message = "Email address cannot be more than 100 characters")
    private String email;
    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 100, message = "Password must be between 8 and 100 characters")
    private String password;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
