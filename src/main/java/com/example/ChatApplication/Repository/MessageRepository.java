package com.example.ChatApplication.Repository;



import com.example.ChatApplication.model.Message;
import com.example.ChatApplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    // Messages where (sender=A and receiver=B) OR (sender=B and receiver=A), ordered by id/time
    List<Message> findBySenderAndReceiverOrReceiverAndSenderOrderByIdAsc(
            User sender1, User receiver1, User sender2, User receiver2);
}

