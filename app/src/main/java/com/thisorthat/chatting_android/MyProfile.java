package com.thisorthat.chatting_android;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyProfile {
    String name;
    private static MyProfile myProfile = null;

    private MyProfile(){
    }

    public static MyProfile getInstance(){
        if(myProfile == null)
            return myProfile = new MyProfile();
        return myProfile;
    }
}
