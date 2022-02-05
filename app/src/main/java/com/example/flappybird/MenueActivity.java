package com.example.flappybird;
//René Beiermann

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.flappybird.game.GameActivity;
import com.example.flappybird.profile.history.History;
import com.example.flappybird.profile.ProfileActivity;
import com.example.flappybird.profile.data.DatabaseAdapter;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MenueActivity extends AppCompatActivity {

    private ImageView playButton;
    private ImageView profileButton;
    private DatabaseAdapter SQLadapter;
    private Handler handler;
    private Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_activtiy);
        this.playButton = findViewById(R.id.playbutton);
        this.profileButton = findViewById(R.id.profilebutton);
        buttonClicks();

        this.SQLadapter = new DatabaseAdapter(this.getApplication());
        this.handler = new Handler(Looper.getMainLooper());
        thread = Thread.currentThread();
        Log.e("test thread 1", String.valueOf(thread.getId()));
        test();

        List<History> history = SQLadapter.getHistory();

        if (history.size() > 0) Log.e("TAG", history.get(0).getDate());
    }

    //should work
    private void test() {
        CompletableFuture<String> username = SQLadapter.getUsername();
        CompletableFuture<Void> voidCompletableFuture = username.thenAccept((user) -> {
            MenueActivity.this.handler.post(() -> {
                Log.e("test thread 2", String.valueOf(thread.getId()));
                if(user != null) Log.e("hallo", user.toString());
            });
        });
    }

    private void buttonClicks() {
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenueActivity.this, GameActivity.class);
                startActivity(intent);
                Log.e("Play", "Here Intent to Game");
            }
        });
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenueActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
    }
}