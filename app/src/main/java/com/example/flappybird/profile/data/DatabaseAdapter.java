package com.example.flappybird.profile.data;
//René Beiermann

import android.app.Application;
import android.util.Log;

import com.example.flappybird.profile.user.User;
import com.example.flappybird.profile.history.History;
import com.example.flappybird.profile.history.HistoryDao;

import java.util.List;
import java.util.concurrent.CompletableFuture;

//IS GOING TO BE USED LATER. WILL RUN IN BACKGROUND THREAD

public class DatabaseAdapter {
    private com.example.flappybird.profile.user.UserDao UserDao;
    private HistoryDao HistoryDao;


    public DatabaseAdapter(Application application) {
        GameDatabase Udb = GameDatabase.getInstance(application);
        this.UserDao = Udb.UserDao();
        this.HistoryDao = Udb.HistoryDao();

    }

    public CompletableFuture<User> insertUsername(final String username) {
        return CompletableFuture.supplyAsync(() -> {
            this.UserDao.insert(new User(username));
            User userForUsername = this.UserDao.getComparedUsername(username);
            return userForUsername;
        }, GameDatabase.databaseWriterExecutorService);
    }

    public CompletableFuture<String> getUsername() {
        return CompletableFuture.supplyAsync(() -> {
            String user = this.UserDao.getUsername();
            Thread thread = Thread.currentThread();
            Log.e("test thread 2", String.valueOf(thread.getId()));
            return user;
        }, GameDatabase.databaseWriterExecutorService);
    }

    public void addAttempt(int score, String dateTime) {
        HistoryDao.insert(new History(score, dateTime));
    }

    public List<History> getHistory() {
        return HistoryDao.getLastTenMatches();
    }
}
