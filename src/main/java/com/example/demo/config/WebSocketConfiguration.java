package com.example.demo.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

// Annotation is required to configure the program
@Configuration
// We need this annotation to utilize the websocket functions
@EnableWebSocketMessageBroker
// WebSocketMessageBrokerConfigurer needs to be extended
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {
// Override the method 
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Set up endpoint which indicates the specific location whithin a web application
        // that Websocket messages are sent or recieved from
        registry.addEndpoint("/ws").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // Set up application desitination
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic");
    }
}