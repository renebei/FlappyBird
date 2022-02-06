//René Beiermann
package com.example.flappybird.profile;

import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.flappybird.R;
import com.example.flappybird.profile.data.DatabaseRepository;
import com.example.flappybird.profile.history.Attempt;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;

public class ProfileModel {

    private DatabaseRepository repo;
    private ProfileActivity activity;
    private TextView a, b, c;

    public ProfileModel(ProfileActivity activity) {
        this.activity = activity;
        this.repo = new DatabaseRepository(activity.getApplication());
    }

    protected void matchHistory() {
        CompletableFuture<List<Attempt>> history = this.repo.getHistory();
        history.thenAccept((List<Attempt> s) -> {
            int id = 0;
            GridLayout gridLayout = (GridLayout) activity.findViewById(R.id.grid);
            int size = s.size();
            for (int i = 0; i < size; i++) {
                TextView field = new TextView(activity);
                field.setX(200);
                field.setY((30*i)+200);
                field.setTextSize(22);
                field.setBackgroundColor(Color.WHITE);
                field.setText(s.get(i).getDate() + " / Score: " + s.get(i).getScore() + "\n");
                gridLayout.addView(field);
            }
            s.forEach(attempt -> Log.e("Logging History", attempt.getDate()));
        });
    }

    protected void checkForRegisteredUser() {
        CompletableFuture<String> history = this.repo.getUsername();
        history.thenAccept((username) -> {
            if (username == null)
                activity.startActivity(new Intent(activity, RegisterActivity.class));
        });
    }
}
