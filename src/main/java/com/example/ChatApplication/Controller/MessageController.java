package com.example.ChatApplication.Controller;



import com.example.ChatApplication.model.Message;
import com.example.ChatApplication.model.User;
import com.example.ChatApplication.Service.ChatService;
import com.example.ChatApplication.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {
    private final ChatService chatService;
    private final UserService userService;

    /**
     * GET /messages/history?userA=alice&userB=bob
     * returns all messages between userA and userB ordered ascending by id/time
     */
    @GetMapping("/history")
    public ResponseEntity<List<Message>> history(@RequestParam String userA, @RequestParam String userB) {
        User a = userService.findByUsername(userA);
        User b = userService.findByUsername(userB);
        if (a == null || b == null) return ResponseEntity.badRequest().build();
        var conv = chatService.getConversation(a, b);
        return ResponseEntity.ok(conv);
    }
}

