package com.recykal.ticketer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
//import lombok.*;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    @JsonProperty("user_id")
    private long id;
    @NotBlank(message = "Full name is required")
    @Size(max = 50, message = "Full name cannot be more than 50 characters")
    @Column(name = "fullname")
    private String fullName;
    @NotBlank(message = "Department is required")
    @Size(max = 50, message = "Department cannot be more than 50 characters")
    @Column(name = "dept")
    private String dept;
    @NotBlank(message = "Email address is required")
    @Email(message = "Invalid email address")
    @Size(max = 100, message = "Email address cannot be more than 100 characters")
    @Column(name = "email", unique = true)
    private String email;
    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 100, message = "Password must be between 8 and 100 characters")
    @JsonIgnore
    private String password;
    @Column(name = "date")
    private ZonedDateTime date;
    @Column(name = "actions")
    private boolean actions = true;

    @Column(name= "role")
    private String role;

    @OneToMany(mappedBy = "user")
    private List<Ticket> tickets;

    @OneToMany(mappedBy = "sender")
    private List<Chat> sentChats;

    @OneToMany(mappedBy = "receiver")
    private List<Chat> receivedChats;

    public Users() {
    }

    public Users(String fullname, String dept, String email, String password, ZonedDateTime date) {
        this.fullName = fullname;
        this.dept = dept;
        this.email = email;
        this.password = password;
        this.date=date;
        //hashPassword();
    }

    public long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public void setFullname(String fullname) {
        this.fullName = fullname;
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

    public void setPassword(String password) {
        this.password = password;
//        hashPassword();
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public boolean isActions() {
        return actions;
    }

    public void setActions(boolean actions) {
        this.actions = actions;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    //    public void hashPassword() {
//        try {
//            MessageDigest md = MessageDigest.getInstance("SHA-256");
//            byte[] hashedPasswordBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
//            this.password = Base64.getEncoder().encodeToString(hashedPasswordBytes);
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException(e);
//        }
//
//    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", dept='" + dept + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", date=" + date +
                ", actions=" + actions +
                ", role='" + role + '\'' +
                ", tickets=" + tickets +
                '}';
    }

}
