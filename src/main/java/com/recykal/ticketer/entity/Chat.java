package com.recykal.ticketer.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "chat")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "timestamp", nullable = false)
    private Timestamp timestamp;

    @ManyToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "user_id", nullable = false)
    @JsonIgnoreProperties(value = { "dept", "date", "actions","email","role" })
    private Users sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id", referencedColumnName = "user_id", nullable = false)
    @JsonIgnoreProperties(value = { "dept", "date", "actions","email","role" })
    private Users receiver;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Users getSender() {
        return sender;
    }

    public void setSender(Users sender) {
        this.sender = sender;
    }

    public Users getReceiver() {
        return receiver;
    }

    public void setReceiver(Users receiver) {
        this.receiver = receiver;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", timestamp=" + timestamp +
                ", sender=" + sender +
                ", receiver=" + receiver +
                '}';
    }
}
