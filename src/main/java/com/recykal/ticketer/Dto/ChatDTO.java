package com.recykal.ticketer.Dto;

import java.sql.Timestamp;

public class ChatDTO {
    private Long id;
    private String message;
    private ChatUsersDTO sender;
    private ChatUsersDTO receiver;

    public ChatDTO(){
    }

    public ChatDTO(Long id, String message,ChatUsersDTO sender, ChatUsersDTO receiver) {
        this.id = id;
        this.message = message;
        this.sender = sender;
        this.receiver = receiver;
    }

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

    public ChatUsersDTO getSender() {
        return sender;
    }

    public void setSender(ChatUsersDTO sender) {
        this.sender = sender;
    }

    public ChatUsersDTO getReceiver() {
        return receiver;
    }

    public void setReceiver(ChatUsersDTO receiver) {
        this.receiver = receiver;
    }
    @Override
    public String toString() {
        return "ChatDTO{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", sender=" + sender +
                ", receiver=" + receiver +
                '}';
    }
}
