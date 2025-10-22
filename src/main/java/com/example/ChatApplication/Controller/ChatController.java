package com.example.ChatApplication.Controller;



import com.example.ChatApplication.model.Message;
import com.example.ChatApplication.Service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatService chatService;

    @MessageMapping("/chat")
    public void sendPrivateMessage(@Payload Message message) {
        // Persist
        chatService.saveMessage(message);

        // Send the message to the receiver's private queue
        messagingTemplate.convertAndSendToUser(
                message.getReceiver().getUsername(),
                "/queue/messages",
                message
        );

        // Send a short notification to receiver's public notifications topic (could be used to show unread counts)
        var notification = new NotificationDto(message.getSender().getUsername(), message.getContent());
        messagingTemplate.convertAndSend("/topic/notifications/" + message.getReceiver().getUsername(), notification);
    }

    // small DTO
    public static record NotificationDto(String from, String preview) {}
}


