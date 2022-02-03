package com.example.flappybird.profile.data;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.flappybird.history.HistoryDao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

//IS GOING TO BE USED LATER. WILL RUN IN BACKGROUND THREAD

public class DatabaseAdapter {
    private UserDao UserDao;
    private HistoryDao HistoryDao;

    public DatabaseAdapter(Application application) {
        UserDatabase Udb = UserDatabase.getInstance(application);
        this.UserDao = Udb.UserDao();
        this.HistoryDao = Udb.HistoryDao();
    }

    public CompletableFuture<User> insertUsername(final String username) {
        return CompletableFuture.supplyAsync(() -> {
            this.UserDao.insert(new User(username));
            User userForUsername = this.UserDao.getComparedUsername(username);

            return userForUsername;
        }, UserDatabase.databaseWriterExecutorService);
    }

    public CompletableFuture<String> getUsername() {
        return CompletableFuture.supplyAsync(() -> {
            String user = this.UserDao.getUsername();

            return user;
        }, UserDatabase.databaseWriterExecutorService);
    }
}
