package com.example.demo.chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

// Annotation for the controller, which spring requires
@Controller
public class ChatController {

    // Receive the message from users
    @MessageMapping("/chat.sendMessage")
    // Send it to the specific location
    @SendTo("/topic/public")
    public ChatMessage sendMessage(
            // Declare the ChatMessage object
            @Payload ChatMessage chatMessage
    ) {
        // return the object
        return chatMessage;
    }

    // When user join the chat 
    @MessageMapping("/chat.addUser")
    // Send the information to the location
    @SendTo("/topic/public")
    public ChatMessage addUser(
            @Payload ChatMessage chatMessage,
            // we need this to operate the websocket session
            SimpMessageHeaderAccessor headerAccessor
    ) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
}