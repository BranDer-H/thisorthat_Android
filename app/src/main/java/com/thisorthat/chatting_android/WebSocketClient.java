package com.thisorthat.chatting_android;

import android.app.Activity;
import android.util.Log;

import com.google.gson.Gson;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class WebSocketClient extends Activity {
    private static final String TAG = WebSocketClient.class.getSimpleName();
    private WebSocket client;
    private static WebSocketClient webSocketClient;

    private WebSocketClient(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //client = new WebSocketFactory().createSocket("ws://3.37.234.201:8080/chat");
                    client = new WebSocketFactory().createSocket("ws://10.0.2.2:8080/chat");
                    Log.d(TAG, "onTextMessage, Thread name: " + Thread.currentThread().getName());
                    client.addListener(new WebSocketAdapter(){
                        @Override
                        public void onConnected(WebSocket websocket, Map<String, List<String>> headers) throws Exception {
                            super.onConnected(websocket, headers);
                            Log.d(TAG, "connected");
                            Log.d(TAG, "onTextMessage, Thread name: " + Thread.currentThread().getName());
                        }

                        @Override
                        public void onTextMessage(WebSocket websocket, String text) throws Exception {
                            Log.d(TAG, "Message received.");
                            Log.d(TAG, "onTextMessage, Thread name: " + Thread.currentThread().getName());
                            super.onTextMessage(websocket, text);
                            Gson gson = new Gson();
                            ChatMessage chatMessage = gson.fromJson(text, ChatMessage.class);
                            Log.d(TAG, chatMessage.toString());

                            ChatAdapter chatAdapter = ChatAdapter.getInstance();
                            chatAdapter.putChatMessage(chatMessage);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    chatAdapter.notifyDataSetChanged();
                                }
                            });

                        }
                    });
                    client.connect();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (WebSocketException e) {
                    e.printStackTrace();
                }
            }
        },"socket thread").start();
    }

    public static WebSocketClient getInstance(){
        if(webSocketClient == null)
            return new WebSocketClient();
        return webSocketClient;
    }

    public void send(ChatMessage chatMessage) {
        Gson gson = new Gson();
        client.sendText(gson.toJson(chatMessage));
    }
}
