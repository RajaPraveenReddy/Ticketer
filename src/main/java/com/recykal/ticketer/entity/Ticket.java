package com.recykal.ticketer.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
public class Ticket {
    @Id
    private String id;

    @NotBlank(message = "Project is required")
    @Size(max = 50, message = "Project cannot be more than 50 characters")
    @Column(name = "project")
    private String project;
    @NotBlank(message = "Status is required")
    @Size(max = 20, message = "Status cannot be more than 20 characters")
    @Column(name = "status")
    private String status;

    @Column(name = "dept")
    private String dept;

    @Column(name = "date")
    private ZonedDateTime date;

    @Column(name = "description")
    private String description;

    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "user_id")
//  @JsonIgnoreProperties(value = { "fullName", "dept", "date", "actions" })
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "fullName", "dept", "date", "actions"})
    private Users user;

    public Ticket() {
        this.id = "TIC" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    public Ticket(String project, String status, Users user) {
        this.project = project;
        this.status = status;
        this.user = user;

    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public String getEmail() {
        return this.getUser().getEmail();
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

//    @Override
//    public String toString() {
//        return "Ticket{" +
//                "id='" + id + '\'' +
//                ", project='" + project + '\'' +
//                ", status='" + status + '\'' +
//                ", dept='" + dept + '\'' +
//                ", date=" + date +
//                ", description='" + description + '\'' +
//                ", email='" + email + '\'' +
//                ", user=" + user +
//                '}';
//    }

}
