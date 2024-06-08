package com.example.demo.config;

import com.example.demo.chat.ChatMessage;
import com.example.demo.chat.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@Slf4j   // this is for login and to determine whether user log out or not
@RequiredArgsConstructor
public class WebSocketEventListener {
    // final keyword is used to declare constants as unmodifiable
    private final SimpMessageSendingOperations messagingTemplate;

    @EventListener
    // Listen to session disconnection 
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        // Get the message from the front end page
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        // Create the username string type
        String username = (String) headerAccessor.getSessionAttributes().get("username");
        // When user disconnect which means they left
        if (username != null) {
            log.info("user disconnected: {}", username);
            //var keyword allows us to declare the variable without specifing the data type
            // Create the chatMessage object
            var chatMessage = ChatMessage.builder()
                    .type(MessageType.LEAVE)
                    .sender(username)
                    .build();
            // This specific URL is where all users can communicate
            messagingTemplate.convertAndSend("/topic/public", chatMessage);
        }
    }

}
