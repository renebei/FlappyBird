package com.example.flappybird.profile;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Gravity;
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

/**
 * @author René Beiermann
 * Diese Klasse beinhaltet alle nötigen Abläufe und Methoden um die Nutzerübersicht zu realisieren.
 * Sie ist somit das ViewModel der ProfileActivity
 */
public class UserStatistics {

    private DatabaseRepository repo;
    private ProfileActivity activity;

    /**
     * @param activity Nimmt Profile Activity um auf die ID zugreifen zu können.
     */
    public UserStatistics(ProfileActivity activity) {
        this.activity = activity;
        this.repo = new DatabaseRepository(activity.getApplication());
    }

    /**
     * Übersicht über die zuletzt gespielten Spiele generieren.
     * Eine Schleife über die letzten 5 gespielten Spiele.
     * Diese als TextView anzeigen.
     */
    protected void matchHistory() {
        CompletableFuture<List<Attempt>> history = this.repo.getHistory();
        history.thenAccept((List<Attempt> s) -> {
            int id = 0;
            GridLayout gridLayout = (GridLayout) activity.findViewById(R.id.grid);
            for (int i = 0; i < 5; i++) {
                TextView field = new TextView(activity);
                field.setX(350);
                field.setY((30*i)+200);
                field.setTextSize(18);
                field.setTypeface(Typeface.MONOSPACE);
                field.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                field.setTextColor(Color.WHITE);
                field.setText(s.get((s.size()-1)-i).getDate() + " / Score: " + s.get((s.size()-1)-i).getScore() + "\n");
                gridLayout.addView(field);
            }
            s.forEach(attempt -> Log.e("Logging History", attempt.getDate()));
        });
    }

    /**
     * Generiert die Anzahl der gespielten Spiele und trägt sie in die TextView mit der passenden ID.
     */
    protected void displayGamesPlayed() {
        CompletableFuture<Integer> gamesPlayed = this.repo.getGamesPlayed();
        gamesPlayed.thenAccept((Integer games) -> {
           TextView field = activity.findViewById(R.id.gamesPlayed);
           field.setText("Games Played: " + games);
           field.setTextColor(Color.WHITE);
        });
    }

    /**
     * Generiert den Namen des Nutzer und trägt diesen in die TextView mit der passenden ID.
     */
    protected void displayUser() {
        CompletableFuture<String> gamesPlayed = this.repo.getUsername();
        gamesPlayed.thenAccept((user) -> {
            TextView field = activity.findViewById(R.id.displayUser);
            field.setText(user);
            field.setTextSize(34);
            field.setShadowLayer(2, 1, 1, Color.RED);
            field.setTextColor(Color.WHITE);
        });
    }

    /**
     * Generiert den Highscore des Nutzer und trägt diesen in die TextView mit der passenden ID.
     */
    protected void displayHighscore() {
        CompletableFuture<Integer> gamesPlayed = this.repo.getHighscore();
        gamesPlayed.thenAccept((Integer highscore) -> {
            TextView field = activity.findViewById(R.id.highscore);
            field.setText("Highscore: " + highscore);
            field.setTextColor(Color.WHITE);
        });
    }
}
