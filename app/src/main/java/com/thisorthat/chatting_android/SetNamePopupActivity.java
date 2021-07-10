package com.thisorthat.chatting_android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SetNamePopupActivity extends Activity {

    EditText inputName;
    TextView ErrorText;
    Button setNameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.set_name_popup);

        inputName = findViewById(R.id.input_name);
        ErrorText = findViewById(R.id.error_textview);
        setNameButton = findViewById(R.id.set_name_button);

        setNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = inputName.getText().toString();
                MyProfile.getInstance().setName(name);
                System.out.println(MyProfile.getInstance().getName());
                WebSocketClient client = WebSocketClient.getInstance();
                ChatMessage chatMessage = new ChatMessage(name, MessageType.JOIN, "", System.currentTimeMillis());
                client.send(chatMessage);
                finish();
            }
        });
    }
}
