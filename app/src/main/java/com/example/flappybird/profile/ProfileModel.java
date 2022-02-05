//René Beiermann
package com.example.flappybird.profile;

import android.content.Intent;
import android.util.Log;
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
    private TextView a,b,c;

    public ProfileModel(ProfileActivity activity) {
        this.activity = activity;
        this.repo = new DatabaseRepository(activity.getApplication());
        this.a = activity.findViewById(R.id.textView);
        this.b = activity.findViewById(R.id.textView2);
        this.c = activity.findViewById(R.id.textView3);

    }

    protected void matchHistory() {
        CompletableFuture<List<Attempt>> history = this.repo.getHistory();
        history.thenAccept((List<Attempt> s) -> {
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
