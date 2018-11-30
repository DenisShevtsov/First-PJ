package ru.startandroid.myapplication;

import com.vk.sdk.api.model.VKApiCity;
import com.vk.sdk.api.model.VKApiUserFull;

public class Friend {
    long id;
    String nickname;
    String sex;
    String photo;
    Friend(String nickname, String sex, String photo){
        this .nickname = nickname;
        this .sex= sex;
        this .photo = photo;
    }

}
