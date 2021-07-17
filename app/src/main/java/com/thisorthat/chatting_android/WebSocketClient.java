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

public class WebSocketClient {
    private static final String TAG = WebSocketClient.class.getSimpleName();
    private final String SERVER_URL = "ws://3.37.234.201:8080/chat";
    private final String LOCAL_URL = "ws://10.0.2.2:8080/chat";
    private WebSocket client;
    private RoomActivity roomActivity;
    private static WebSocketClient webSocketClient;

    private WebSocketClient(){
        try {
            client = new WebSocketFactory().createSocket(LOCAL_URL);
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

                    /**
                     * TODO: Message Handler 클래스 분리 필요.
                     */
                    ChatAdapter chatAdapter = roomActivity.getChatAdapter();
                    switch(chatMessage.getMessageType()){
                        case JOIN:
                        case CHAT:
                        case LEAVE:
                            chatAdapter.putChatMessage(chatMessage);
                            break;
                        case ERROR:
                            /** TODO: ERROR 메시지 처리 필요 */
                            break;
                        case PARTICIPANTS:
                            /** TODO: 참여자 이름 처리 필요 */
                            Log.d(TAG, "Participants: " + chatMessage.getContent());
                            break;
                    }
                    roomActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            chatAdapter.notifyDataSetChanged();
                            roomActivity.getChatRecyclerView().scrollToPosition(chatAdapter.getItemCount()-1);
                        }
                    });

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setRoomActivity(RoomActivity roomActivity){
        this.roomActivity = roomActivity;
    }

    public static WebSocketClient getInstance(){
        if(webSocketClient == null)
            return webSocketClient = new WebSocketClient();
        return webSocketClient;
    }

    public void connect(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    client.connect();
                } catch (WebSocketException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void send(ChatMessage chatMessage) {
        Gson gson = new Gson();
        client.sendText(gson.toJson(chatMessage));
    }

    public void closeConnection(){
        client.sendClose();
    }
}
