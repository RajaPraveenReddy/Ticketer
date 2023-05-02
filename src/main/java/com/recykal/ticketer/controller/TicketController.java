package com.recykal.ticketer.controller;

import com.recykal.ticketer.Dto.TicketDTO;
import com.recykal.ticketer.Exception.ResourceNotFoundException;
import com.recykal.ticketer.entity.Ticket;
import com.recykal.ticketer.entity.Users;
import com.recykal.ticketer.repository.TicketRepository;
import com.recykal.ticketer.service.TicketService;
import com.recykal.ticketer.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserService usersService;

    @PostMapping("/users/createTicket")
    public ResponseEntity<Ticket> createTicket(@Valid @RequestBody TicketDTO ticket) throws ResourceNotFoundException {
        Ticket savedTicket = ticketService.createTicket(ticket);
        return ResponseEntity.ok(savedTicket);
    }

    @GetMapping("/users/{userId}/tickets")
    public ResponseEntity<List<Ticket>> getTicketsByUser(@PathVariable Long userId) throws ResourceNotFoundException {
        Users user = usersService.getUsersById(userId);
        List<Ticket> tickets = ticketService.getTicketsByUser(user);
        return ResponseEntity.ok().body(tickets);
    }

    @GetMapping("/{user_id}/countByStatus")
    public Map<String, Object> countTicketsByStatus(@PathVariable Long user_id) {
        return ticketRepository.ticketCountByStatus(user_id);
    }

    @GetMapping("/users/tickets")
    public List<Map<String,Object>> getAllTickets(){
        return ticketService.findALlTickets();
    }

    @GetMapping("/tickets/count")
    public List<Map<String,Object>> countTicketsByUser() {
        return ticketService.countTicketsByUser();
    }

    @GetMapping("/user/{userId}/count/last5days")
    public List<Map<String,Object>> countTicketsByUserIdLast5Days(@PathVariable Long userId) {
        return ticketService.countTicketsByUserIdLast5Days(userId);
    }

    @GetMapping("/byStatus/{status}")
    public List<Ticket> getTicketByStatus(@PathVariable String status){
        return ticketService.getTicketByStatus(status);
    }
}
