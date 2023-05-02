package com.recykal.ticketer.service;

import com.recykal.ticketer.Dto.ChatDTO;
import com.recykal.ticketer.Dto.ChatUsersDTO;
import com.recykal.ticketer.Exception.ResourceNotFoundException;
import com.recykal.ticketer.entity.Chat;
import com.recykal.ticketer.entity.Users;
import com.recykal.ticketer.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChatService {
    @Autowired
    public ChatRepository chatRepository;
    @Autowired
    public UserService userService;

    public Chat save(ChatDTO chatDto) throws ResourceNotFoundException {
        Users sender = userService.getUsersById(chatDto.getSender().getId());
        Users receiver = userService.getUsersById(chatDto.getReceiver().getId());
        if (sender == null || receiver == null) {
            throw new ResourceNotFoundException("User not found");
        }
        Chat chat = new Chat();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        chat.setMessage(chatDto.getMessage());
        chat.setTimestamp(timestamp);
        chat.setSender(sender);
        chat.setReceiver(receiver);
        Chat savedChat = chatRepository.save(chat);
        return savedChat;
    }

    public List<ChatDTO> getChatsByUsers(Long senderId, Long receiverId) throws ResourceNotFoundException {
        Users sender = userService.getUsersById(senderId);
        Users receiver = userService.getUsersById(receiverId);
        if (sender == null || receiver == null) {
            throw new ResourceNotFoundException("User not found");
        }
        List<Chat> chats = chatRepository.findBySenderAndReceiverOrderByTimestampAsc(sender, receiver);
        List<ChatDTO> chatDtos = new ArrayList<>();
        for (Chat chat : chats) {
            ChatDTO chatDto = new ChatDTO();
            chatDto.setId(chat.getId());
            chatDto.setMessage(chat.getMessage());
            ChatUsersDTO senderDto = new ChatUsersDTO();
            senderDto.setId(chat.getSender().getId());
            senderDto.setFullName(chat.getSender().getFullName());
            chatDto.setSender(senderDto);
            ChatUsersDTO receiverDto = new ChatUsersDTO();
            receiverDto.setId(chat.getReceiver().getId());
            receiverDto.setFullName(chat.getReceiver().getFullName());
            chatDto.setReceiver(receiverDto);
            chatDtos.add(chatDto);
        }
        return chatDtos;
    }
}
