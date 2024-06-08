package com.example.demo.chat;

// Message type has three different types, which essentially mean the information that will be on the board
// CHAT is the text message that user input 
// JOIN is to inform people that user join the chat
// LEAVE is to inform people that user leaves the chat
public enum MessageType {

    CHAT,
    JOIN,
    LEAVE
}