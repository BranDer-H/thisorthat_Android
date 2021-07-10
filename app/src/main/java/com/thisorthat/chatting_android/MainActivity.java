package com.thisorthat.chatting_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    WebSocketClient client;
    RecyclerView chatRecyclerView;
    Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        chatRecyclerView = findViewById(R.id.chat_contents_recyclerview);
        chatRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        chatRecyclerView.setAdapter(ChatAdapter.getInstance());

        sendButton = findViewById(R.id.send_button);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText messageContent = findViewById(R.id.input_text);
                String text = messageContent.getText().toString();
                ChatMessage chatMessage = new ChatMessage("Kim", MessageType.CHAT, text, System.currentTimeMillis());
                client.send(chatMessage);
                messageContent.getText().clear();
            }
        });

        Log.d(TAG, "onCreate, Thread name: " + Thread.currentThread().getName());
        client = new WebSocketClient();
    }
}