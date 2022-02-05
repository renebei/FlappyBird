package com.example.flappybird.game;

import android.graphics.Point;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.example.flappybird.R;
import com.example.flappybird.game.logic.Game;


public class GameActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Game game = new Game(this);
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