package com.example.flappybird.profile;
//René Beiermann

import androidx.appcompat.app.AppCompatActivity;

import com.example.flappybird.profile.data.DatabaseRepository;
import com.example.flappybird.profile.history.Attempt;
import com.example.flappybird.R;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ProfileActivity extends AppCompatActivity {

    private DatabaseRepository repo;
    //Model

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.repo = new DatabaseRepository(this.getApplication());
        checkForRegisteredUser();
        setContentView(R.layout.activity_profile);
        //Model.MatchHistory
    }

    private void matchHistory() {
        CompletableFuture<List<Attempt>> history = this.repo.getHistory();
        history.thenAccept((List<Attempt> s) -> {
            s.forEach(attempt -> Log.e("Logging History", attempt.getDate()));
        });
    }

    private void checkForRegisteredUser() {
        CompletableFuture<String> history = this.repo.getUsername();
        history.thenAccept((username) -> {
            if (username == null)
                startActivity(new Intent(ProfileActivity.this, RegisterActivity.class));
        });
    }
}