package com.example.demo.chat;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

// ChatMessage object contains attributes of Message type (Another object)
// and content of the message and the the user name of the message
public class ChatMessage {

    private MessageType type;
    private String content;
    private String sender;

}