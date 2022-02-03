package com.example.flappybird.profile;

import androidx.appcompat.app.AppCompatActivity;

import com.example.flappybird.profile.data.User;
import com.example.flappybird.profile.data.UserDao;
import com.example.flappybird.R;
import com.example.flappybird.profile.data.UserDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class ProfileActivity extends AppCompatActivity {

    UserDao UDao;
    UserDatabase Udb;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Udb = UserDatabase.getInstance(this);
        UDao = Udb.UserDao();
        if (UDao.getUser() == null) {
            Intent intent = new Intent(ProfileActivity.this, RegisterActivity.class);
            startActivity(intent);
        }
        Log.e("check", "profile");
        setContentView(R.layout.activity_profile);
    }
}