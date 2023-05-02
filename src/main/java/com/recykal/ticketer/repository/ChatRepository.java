package com.recykal.ticketer.repository;

import com.recykal.ticketer.entity.Chat;
import com.recykal.ticketer.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat,Long> {
//    List<Chat> findBySenderAndReceiver(Users sender, Users receiver);
//@Query(value = "SELECT * FROM chat WHERE (sender_id = :senderId AND receiver_id = :receiverId) OR (sender_id = :receiverId AND receiver_id = :senderId) ORDER BY timestamp ASC", nativeQuery = true)
List<Chat> findBySenderAndReceiverOrderByTimestampAsc(@Param("senderId") Users sender, @Param("receiverId") Users receiver);
}
