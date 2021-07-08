package com.thisorthat.chatting_android;

import android.util.Log;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class WebSocketClient {
    private static final String TAG = WebSocketClient.class.getSimpleName();
    private WebSocket client;

    public WebSocketClient(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //client = new WebSocketFactory().createSocket("ws://3.37.234.201:8080/chat");
                    client = new WebSocketFactory().createSocket("ws://10.0.2.2:8080/chat");
                    client.addListener(new WebSocketAdapter(){
                        @Override
                        public void onConnected(WebSocket websocket, Map<String, List<String>> headers) throws Exception {
                            super.onConnected(websocket, headers);
                            Log.d(TAG, "connected");
                        }

                        @Override
                        public void onTextMessage(WebSocket websocket, String text) throws Exception {
                            super.onTextMessage(websocket, text);
                        }
                    });
                    client.connect();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (WebSocketException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
