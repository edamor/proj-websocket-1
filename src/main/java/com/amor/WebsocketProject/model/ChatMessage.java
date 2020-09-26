package com.amor.WebsocketProject.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
public class ChatMessage
{
    @Setter
    @Getter
    private MessageType type;

    @Setter
    @Getter
    private String content;

    @Setter
    @Getter
    private String sender;

    @Setter
    @Getter
    private String time;



}
