package com.example.flappybird.profile;
//René Beiermann

import androidx.appcompat.app.AppCompatActivity;
import com.example.flappybird.profile.user.UserDao;
import com.example.flappybird.R;
import com.example.flappybird.profile.data.GameDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class ProfileActivity extends AppCompatActivity {

    UserDao UDao;
    GameDatabase Udb;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Udb = GameDatabase.getInstance(this);
        UDao = Udb.UserDao();
        if (UDao.getUsername() == null) {
            Intent intent = new Intent(ProfileActivity.this, RegisterActivity.class);
            startActivity(intent);
        } else {
            Log.e("check", UDao.getUsername());
        }
        setContentView(R.layout.activity_profile);
    }
}