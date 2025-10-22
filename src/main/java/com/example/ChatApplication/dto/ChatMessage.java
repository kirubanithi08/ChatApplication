package com.example.ChatApplication.dto;

import com.example.ChatApplication.model.User;
import lombok.Data;

@Data
public class ChatMessage {
    private String content;
    private User sender;
    private User receiver;
}