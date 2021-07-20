package com.thisorthat.chatting_android;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

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
    private long timestamp;
    private String color;

    public String getTransferedTime(){
        Date date = new Date(timestamp);
        DateFormat formatter = new SimpleDateFormat("HH:mm");
        formatter.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
        String dateFormatted = formatter.format(date);
        int hours = Integer.valueOf(dateFormatted.substring(0, 2));
        int minutes = Integer.valueOf(dateFormatted.substring(3));
        String day;
        if(hours <= 12) {
            day = "오전";
        }
        else {
            day = "오후";
            hours -= 12;
        }
        return day + " " + String.valueOf(hours) + "시 " + String.valueOf(minutes) + "분";
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "name='" + name + '\'' +
                ", messageType=" + messageType +
                ", content='" + content + '\'' +
                ", timestamp=" + timestamp +
                ", color=" + color +
                '}';
    }
}
