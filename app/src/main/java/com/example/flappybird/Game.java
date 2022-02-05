package com.example.flappybird;

import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;


public class Game extends AppCompatActivity {
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        com.example.flappybird.gamelogic.Game game = new com.example.flappybird.gamelogic.Game(this);
        Thread thread = new Thread(game);
        thread.start();

        findViewById(R.id.surfaceView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.jump();
            }
        });
    }

    public SurfaceHolder getHolder(){
        return ((SurfaceView)findViewById(R.id.surfaceView)).getHolder();
    }

    public Point getPhoneSize(){
        Point p = new Point();
        getWindowManager().getDefaultDisplay().getSize(p);
        return p;
    }
}