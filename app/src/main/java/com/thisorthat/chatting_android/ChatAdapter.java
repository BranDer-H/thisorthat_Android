package com.thisorthat.chatting_android;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    List<ChatMessage> messages;

    public ChatAdapter(){
        messages = new ArrayList<ChatMessage>();
        messages.add(new ChatMessage("Kim", MessageType.CHAT, "Hello"));
        messages.add(new ChatMessage("Hong", MessageType.CHAT, "world"));
        messages.add(new ChatMessage("Kim", MessageType.CHAT, "Hello"));
        messages.add(new ChatMessage("Hong", MessageType.CHAT, "chat"));
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View chatItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item, parent, false);
        return new ChatViewHolder(chatItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.ChatViewHolder holder, int position) {
        holder.name.setText(messages.get(position).getName());
        holder.content.setText(messages.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView content;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.chat_item_name);
            content = itemView.findViewById(R.id.chat_item_content);
        }
    }
}
