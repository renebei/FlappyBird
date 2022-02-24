package com.example.flappybird.game;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.flappybird.MenueActivity;
import com.example.flappybird.R;
import com.example.flappybird.game.logic.Game;

/**
 * @author David Siegbert
 * Diese Activität verwaltet den Canvas, auf welchem das Spiel läuft
 */
public class GameActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Game game = new Game(this);
        Thread thread = new Thread(game);
        thread.start();

        findViewById(R.id.surfaceView).setOnClickListener(view -> {
            if (!game.getPlayer().isDead())
                game.jump();
            else
                startActivity(new Intent(GameActivity.this, MenueActivity.class));
        });
    }

    /**
     * Get the SurfaceHolder
     * @return SurfaceHolder to lock the canvas in thread
     */
    public SurfaceHolder getHolder() {
        return ((SurfaceView) findViewById(R.id.surfaceView)).getHolder();
    }

    /**
     * Get the device Size
     * @return the size as a point
     */
    public Point getPhoneSize() {
        Point p = new Point();
        getWindowManager().getDefaultDisplay().getSize(p);
        return p;
    }
}