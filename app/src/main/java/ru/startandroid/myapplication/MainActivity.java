package ru.startandroid.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.methods.VKApiFriends;
import com.vk.sdk.api.model.VKApiUserFull;
import com.vk.sdk.api.model.VKList;
import com.vk.sdk.api.model.VKUsersArray;
import com.vk.sdk.util.VKUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class MainActivity extends AppCompatActivity {
    private String[] scop = new String[] {VKScope.FRIENDS, VKScope.WALL};
    private RecyclerView rv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VKSdk.login(this, scop);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                rv = (RecyclerView) findViewById(R.id.rv);
                LinearLayoutManager llm = new LinearLayoutManager(MainActivity.this);
                rv.setLayoutManager(llm);


                VKRequest request = VKApi.friends().get(VKParameters.from(VKApiConst.FIELDS, "photo_50,sex"));
                request.executeWithListener(new VKRequest.VKRequestListener() {
                    @Override
                    public void onComplete(VKResponse response) {
                        super.onComplete(response);

                        VKUsersArray usersArray = (VKUsersArray) response.parsedModel;
                        List<Friend> friends;
                        friends = new ArrayList<>();
                        String sex = "";


                        for (int i=0; i<usersArray.size(); i++){

                            if(usersArray.get(i).sex == 1) {
                                sex = "Жен";
                            } else if (usersArray.get(i).sex == 2){
                                sex = "Муж";
                            } else { sex = "Непонятно";}

                            friends.add(new Friend(usersArray.get(i).first_name +
                            " " + usersArray.get(i).last_name, sex, usersArray.get(i).photo_50));
                            sex = "";
                        }

                        RVAdapter adapter = new RVAdapter(friends);
                        rv.setAdapter(adapter);
                    }
                });
                // Пользователь успешно авторизовался
            }
            @Override
            public void onError(VKError error) {
                // Произошла ошибка авторизации (например, пользователь запретил авторизацию)
            }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
