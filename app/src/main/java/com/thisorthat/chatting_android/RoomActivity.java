package com.thisorthat.chatting_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import lombok.Getter;

@Getter
public class RoomActivity extends AppCompatActivity {
    private static final String TAG = RoomActivity.class.getSimpleName();

    WebSocketClient client;
    RecyclerView chatRecyclerView;
    ChatAdapter chatAdapter;
    Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        client = WebSocketClient.getInstance();
        client.setRoomActivity(this);

        chatRecyclerView = findViewById(R.id.chat_contents_recyclerview);
        chatRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        chatAdapter = new ChatAdapter();
        //chatAdapter.putChatMessage(new ChatMessage("system", MessageType.JOIN, MyProfile.getInstance().getName() + "님이 입장했습니다.", 0));
        chatRecyclerView.setAdapter(chatAdapter);

        ChatMessage chatMessage = new ChatMessage(MyProfile.getInstance().getName(), MessageType.JOIN, "", System.currentTimeMillis());
        client.send(chatMessage);

        sendButton = findViewById(R.id.send_button);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText messageContent = findViewById(R.id.input_text);
                String text = messageContent.getText().toString();
                ChatMessage chatMessage = new ChatMessage(MyProfile.getInstance().getName(), MessageType.CHAT, text, System.currentTimeMillis());
                client.send(chatMessage);
                messageContent.getText().clear();
            }
        });
        Log.d(TAG, "onCreate, Thread name: " + Thread.currentThread().getName());

        client = WebSocketClient.getInstance();

    }

}