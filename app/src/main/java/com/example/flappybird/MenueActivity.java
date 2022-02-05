package com.example.flappybird;
//René Beiermann

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.flappybird.game.GameActivity;
import com.example.flappybird.profile.ProfileActivity;
import com.example.flappybird.profile.data.DatabaseRepository;

public class MenueActivity extends AppCompatActivity {

    private ImageView playButton;
    private ImageView profileButton;
    private DatabaseRepository repo;
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_activtiy);

        this.repo = new DatabaseRepository(this.getApplication());
        this.playButton = findViewById(R.id.playbutton);
        this.profileButton = findViewById(R.id.profilebutton);

        buttonClicks();
    }

    private void buttonClicks() {
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenueActivity.this, GameActivity.class));
            }
        });
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenueActivity.this, ProfileActivity.class));
            }
        });
    }
}