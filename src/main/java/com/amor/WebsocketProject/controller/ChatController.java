package com.amor.WebsocketProject.controller;

import com.amor.WebsocketProject.model.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController
{
    @Autowired
    private SimpMessageSendingOperations sendingOperations;

    @MessageMapping("/chat.send")
    public void sendMessage(@Payload final ChatMessage chatMessage)
    {
        System.out.println(chatMessage.getSender() + "\n" + chatMessage.getContent());
        sendingOperations.convertAndSend("/topic/public", chatMessage);
    }


    @MessageMapping("/chat.newUser")
    @SendTo("/topic/public")
    public ChatMessage newUser(@Payload final ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor)
    {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
}
