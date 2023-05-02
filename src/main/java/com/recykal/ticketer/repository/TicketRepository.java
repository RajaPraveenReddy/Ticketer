package com.recykal.ticketer.repository;

import com.recykal.ticketer.entity.Ticket;
import com.recykal.ticketer.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface TicketRepository extends JpaRepository<Ticket,String> {
    List<Ticket> findByUser(Users user);

    @Query(value = "SELECT user_id as user_id, COUNT(*) as ticket_count FROM ticket GROUP BY user_id", nativeQuery = true)
    List<Map<String,Object>> countTicketsByUser();

    @Query(value = "SELECT DATE(date) as date, COUNT(*) as count FROM ticket WHERE user_id = :user_id AND date >= DATE(NOW()) - INTERVAL 5 DAY GROUP BY DATE(date)", nativeQuery = true)
    List<Map<String,Object>> countTicketsByUserLast5Days(@Param("user_id") Long userId);

    @Query(value = "SELECT " +
            "  SUM(CASE WHEN status = 'open' THEN 1 ELSE 0 END) AS open_count, " +
            "  SUM(CASE WHEN status = 'inprogress' THEN 1 ELSE 0 END) AS inprogress_count, " +
            "  SUM(CASE WHEN status = 'closed' THEN 1 ELSE 0 END) AS closed_count " +
            "FROM ticket " +
            "WHERE user_id = :user_id", nativeQuery = true)
        Map<String, Object> ticketCountByStatus(@Param("user_id") Long id);

    @Query(value = "SELECT t.id as Ticket_id, t.email as email, t.status as status, t.date as date FROM ticket t", nativeQuery = true)
    List<Map<String,Object>> findAllTickets();

    @Query(value = "SELECT * FROM ticket t where TRIM(t.status) LIKE %:status%", nativeQuery = true)
    List<Ticket> findByTicketStatus(@Param("status") String status);
}
