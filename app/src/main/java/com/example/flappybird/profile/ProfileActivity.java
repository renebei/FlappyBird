package com.example.flappybird.profile;
//René Beiermann

import androidx.appcompat.app.AppCompatActivity;

import com.example.flappybird.MenueActivity;
import com.example.flappybird.profile.data.DatabaseRepository;
import com.example.flappybird.profile.history.Attempt;
import com.example.flappybird.R;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ProfileActivity extends AppCompatActivity {

    private ProfileModel model;
    private Button backToHome;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.model = new ProfileModel(this);
        model.checkForRegisteredUser();
        setContentView(R.layout.activity_profile);
        this.backToHome = findViewById(R.id.profileToHome);
        model.matchHistory();
        model.displayGamesPlayed();
        model.displayUser();
        model.displayHighscore();
        buttonPressed();
    }

    private void buttonPressed() {
        backToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, MenueActivity.class));
            }
        });
    }
}