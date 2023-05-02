package com.recykal.ticketer.service;

import com.recykal.ticketer.Dto.TicketDTO;
import com.recykal.ticketer.Exception.NoTicketsFoundException;
import com.recykal.ticketer.Exception.ResourceNotFoundException;
import com.recykal.ticketer.entity.Ticket;
import com.recykal.ticketer.entity.Users;
import com.recykal.ticketer.repository.TicketRepository;
import com.recykal.ticketer.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;


@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UsersRepository userRepository;

    public Ticket createTicket(TicketDTO ticket) throws ResourceNotFoundException {
        Users user = userRepository.findById(ticket.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id : "+ticket.getUserId()));
        Ticket tickets = new Ticket();
        tickets.setStatus(ticket.getStatus());
        tickets.setProject(ticket.getProject());
        tickets.setDescription(ticket.getDescription());
        tickets.setDate(ZonedDateTime.now());
        tickets.setDept(user.getDept());
        tickets.setEmail(user.getEmail());
        tickets.setUser(user);
        return ticketRepository.save(tickets);
    }
    public List<Ticket> getTicketsByUser(Users user) throws ResourceNotFoundException {
        Users users = userRepository.findById(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id : " + user.getId()));

        List<Ticket> tickets = ticketRepository.findByUser(user);

        if (tickets.isEmpty()) {
            throw new NoTicketsFoundException("No tickets found for the user with ID " + user.getId());
        }

        return tickets;
    }

    public List<Map<String,Object>> countTicketsByUser() {
        return ticketRepository.countTicketsByUser();
    }
    public List<Map<String,Object>> countTicketsByUserIdLast5Days(Long userId) {
        return ticketRepository.countTicketsByUserLast5Days(userId);
    }

    public List<Ticket> getTicketByStatus(String status){
        String trimmedStatus = status.trim();
        return ticketRepository.findByTicketStatus(trimmedStatus);
    }

    public List<Map<String,Object>> findALlTickets(){
        return ticketRepository.findAllTickets();
    }

}
