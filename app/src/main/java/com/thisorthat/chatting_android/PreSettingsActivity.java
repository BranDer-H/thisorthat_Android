package com.thisorthat.chatting_android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import java.io.Serializable;

public class PreSettingsActivity extends Activity implements Serializable{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WebSocketClient.getInstance().connect();

        Intent intent = new Intent(this, SetNamePopupActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onDestroy();
        WebSocketClient.getInstance().closeConnection();
        System.exit(0);
    }
}
