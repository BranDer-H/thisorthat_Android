package com.thisorthat.chatting_android;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    List<ChatMessage> messages;

    public ChatAdapter(){
        messages = new ArrayList<ChatMessage>();
        messages.add(new ChatMessage("system", MessageType.JOIN, "Kim님이 입장했습니다.", System.currentTimeMillis()));
        messages.add(new ChatMessage("Kim", MessageType.CHAT, "Hello. My name is Kim.", System.currentTimeMillis()));
        messages.add(new ChatMessage("Kim", MessageType.CHAT, "Hello", System.currentTimeMillis()));
        messages.add(new ChatMessage("system", MessageType.JOIN, "Hong님이 입장했습니다.", System.currentTimeMillis()));
        messages.add(new ChatMessage("Hong", MessageType.CHAT, "Hello Kim. My name is Hong", System.currentTimeMillis()));
        messages.add(new ChatMessage("Kim", MessageType.CHAT, "Hello", System.currentTimeMillis()));
        messages.add(new ChatMessage("Hong", MessageType.CHAT, "Hello Kim. My name is Hong", System.currentTimeMillis()));
        messages.add(new ChatMessage("Kim", MessageType.CHAT, "Hello", System.currentTimeMillis()));
        messages.add(new ChatMessage("Hong", MessageType.CHAT, "Hello Kim. My name is Hong", System.currentTimeMillis()));
        messages.add(new ChatMessage("Hong", MessageType.CHAT, "Hello Kim. My name is Hong", System.currentTimeMillis()));
        messages.add(new ChatMessage("Kim", MessageType.CHAT, "Hello", System.currentTimeMillis()));
        messages.add(new ChatMessage("Hong", MessageType.CHAT, "Hello Kim. My name is Hong", System.currentTimeMillis()));
        messages.add(new ChatMessage("Hong", MessageType.CHAT, "Hello Kim. My name is Hong", System.currentTimeMillis()));
        messages.add(new ChatMessage("Kim", MessageType.CHAT, "Hello", System.currentTimeMillis()));
        messages.add(new ChatMessage("Hong", MessageType.CHAT, "Hello Kim. My name is Hong", System.currentTimeMillis()));
    }

    @Override
    public int getItemViewType(int position) {
        ChatMessage message = messages.get(position);
        if(message.getMessageType().equals(MessageType.JOIN))
            return 0;
        else if(message.getName().equals("Kim"))
            return 1;
        else
            return 2;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View chatItem;
        switch(viewType){
            case 0:
                chatItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_system_message, parent, false);
                break;
            case 1:
                chatItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_my_message, parent, false);
                break;
            case 2:
                chatItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_other_message, parent, false);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + viewType);
        }
        return new ChatViewHolder(chatItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.ChatViewHolder holder, int position) {
        ChatMessage message = messages.get(position);
        if(message.getMessageType().equals(MessageType.JOIN)){
            holder.content.setText(message.getContent());
        } else if(message.getName().equals("Kim")){
            holder.content.setText(message.getContent());
            holder.timestamp.setText(String.valueOf(message.getTransferedTime()));
        } else{
            holder.name.setText(message.getName());
            holder.content.setText(message.getContent());
            holder.timestamp.setText(String.valueOf(message.getTransferedTime()));
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView content;
        TextView timestamp;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            int viewId = itemView.getId();
            if(viewId == R.id.system_messaage){
                content = itemView.findViewById(R.id.message_content);
            } else if (viewId == R.id.my_message){
                content = itemView.findViewById(R.id.message_content);
                timestamp = itemView.findViewById(R.id.message_time);
            } else if(viewId == R.id.other_message){
                name = itemView.findViewById(R.id.message_name);
                content = itemView.findViewById(R.id.message_content);
                timestamp = itemView.findViewById(R.id.message_time);
            }
        }
    }
}
