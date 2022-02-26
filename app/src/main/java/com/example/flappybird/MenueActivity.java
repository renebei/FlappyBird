package com.example.flappybird;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.flappybird.game.GameActivity;
import com.example.flappybird.profile.ProfileActivity;
import com.example.flappybird.profile.RegisterActivity;
import com.example.flappybird.profile.data.DatabaseRepository;

import java.util.concurrent.CompletableFuture;

/**
 * @author René Beiermann
 * Launcher Activity der App.
 * Diese Acitivity verwaltet das Menü.
 * Leitet den Nutzer zur Registierung wenn er nicht bereits registriert ist.
 */
public class MenueActivity extends AppCompatActivity {

    private ImageView playButton;
    private ImageView profileButton;
    private DatabaseRepository repo;
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.repo = new DatabaseRepository(this.getApplication());
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.menue_activtiy);


        this.playButton = findViewById(R.id.playbutton);
        this.profileButton = findViewById(R.id.profilebutton);

        buttonClicks();
    }

    /**
     * On Click Listener für die Buttons
     */
    private void buttonClicks() {
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkForRegisteredUser();
                startActivity(new Intent(MenueActivity.this, GameActivity.class));
            }
        });
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkForRegisteredUser();
                startActivity(new Intent(MenueActivity.this, ProfileActivity.class));
            }
        });
    }

    /**
     * Wenn der Nutzer nicht registriert ist wird dieser in die RegistierActivity geführt.
     * @see RegisterActivity
     */
    private void checkForRegisteredUser() {
        CompletableFuture<String> history = this.repo.getUsername();
        history.thenAccept((username) -> {
            if (username == null)
                startActivity(new Intent(MenueActivity.this, RegisterActivity.class));
        });
    }
}