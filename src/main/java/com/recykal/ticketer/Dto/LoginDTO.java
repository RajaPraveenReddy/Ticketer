package com.recykal.ticketer.Dto;

public class LoginDTO {
    private String email;
    private String password;
    private boolean actions;


    public LoginDTO() {
    }
    public LoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }

}
