package com.example.flappybird;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MenueActivity extends AppCompatActivity {

    private ImageView background;
    private ImageView playButton;
    private ImageView profileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_activtiy);
        this.background = findViewById(R.id.backg);
        this.playButton = findViewById(R.id.playbutton);
        this.profileButton = findViewById(R.id.profilebutton);
        background.setZ(-20);
        buttonClicks();
    }

    private void buttonClicks() {
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Play", "Here Intent to Game");
            }
        });
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Profile", "2");
            }
        });
    }
}