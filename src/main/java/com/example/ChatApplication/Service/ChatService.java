package com.example.ChatApplication.Service;



import com.example.ChatApplication.model.Message;
import com.example.ChatApplication.model.User;
import com.example.ChatApplication.Repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final MessageRepository messageRepository;

    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    public List<Message> getConversation(User a, User b) {
        return messageRepository.findBySenderAndReceiverOrReceiverAndSenderOrderByIdAsc(a, b, b, a);
    }
}


