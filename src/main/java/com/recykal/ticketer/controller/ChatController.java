package com.recykal.ticketer.controller;

import com.recykal.ticketer.Dto.ChatDTO;
import com.recykal.ticketer.Exception.ResourceNotFoundException;
import com.recykal.ticketer.entity.Chat;
import com.recykal.ticketer.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class ChatController {
    @Autowired
    public ChatService chatService;

    @PostMapping("/chats")
    public ResponseEntity<Chat> saveChat(@RequestBody ChatDTO chatDto) throws ResourceNotFoundException {
        Chat savedChatDto = chatService.save(chatDto);
        return ResponseEntity.ok(savedChatDto);
    }

    @GetMapping("/getChats/{senderId}/{receiverId}")
    public ResponseEntity<List<ChatDTO>> getChatsByUsers(@PathVariable Long senderId, @PathVariable Long receiverId) throws ResourceNotFoundException {
        List<ChatDTO> chats = chatService.getChatsByUsers(senderId, receiverId);
        return ResponseEntity.ok(chats);
    }
}
