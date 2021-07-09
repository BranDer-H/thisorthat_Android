package com.thisorthat.chatting_android;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ChatMessage {
    private String name;
    private MessageType messageType;
    private String content;
}
