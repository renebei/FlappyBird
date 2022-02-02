package com.example.flappybird.profile;

import androidx.appcompat.app.AppCompatActivity;
import com.example.flappybird.profile.data.UserDao;
import com.example.flappybird.R;
import com.example.flappybird.profile.data.UserDatabase;

import android.content.Intent;
import android.os.Bundle;

public class ProfileActivity extends AppCompatActivity {

    UserDao UDao;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (UDao.getUser()[0] == null) {
            Intent intent = new Intent(ProfileActivity.this, RegisterActivity.class);
            startActivity(intent);
        }
        setContentView(R.layout.activity_profile);
    }
}