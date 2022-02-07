package com.example.flappybird.profile.data;
//René Beiermann

import android.app.Application;

import com.example.flappybird.profile.history.Attempt;
import com.example.flappybird.profile.user.User;
import com.example.flappybird.profile.history.HistoryDao;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class DatabaseRepository {
    private com.example.flappybird.profile.user.UserDao UserDao;
    private HistoryDao HistoryDao;


    public DatabaseRepository(Application application) {
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
            return user;
        }, GameDatabase.databaseWriterExecutorService);
    }

    public CompletableFuture<Attempt> addAttempt(int score, String dateTime) {
        return CompletableFuture.supplyAsync(() ->{
            Attempt attempt = new Attempt(score, dateTime);
            this.HistoryDao.insert(attempt);
            return attempt;
        }, GameDatabase.databaseWriterExecutorService);
    }

    public CompletableFuture<List<Attempt>> getHistory() {
        return CompletableFuture.supplyAsync(() ->{
            List<Attempt> attempt = HistoryDao.getLastFiveMatches();
            return attempt;
        }, GameDatabase.databaseWriterExecutorService);
    }

    public CompletableFuture<Void> incrementGamesPlayed() {
        return CompletableFuture.supplyAsync(() -> {
            UserDao.UpdateGamesPlayed(UserDao.getUsername());
            return null;
        }, GameDatabase.databaseWriterExecutorService);
    }

    public CompletableFuture<Integer> getGamesPlayed() {
        return CompletableFuture.supplyAsync(() -> {
            return UserDao.getGamesPlayed();
        }, GameDatabase.databaseWriterExecutorService);
    }

    public CompletableFuture<Integer> getHighscore() {
        return CompletableFuture.supplyAsync(() -> {
           return UserDao.getHighscore(UserDao.getUsername());
        }, GameDatabase.databaseWriterExecutorService);
    }

    public CompletableFuture<Void> updateHighscore(int score) {
        return CompletableFuture.supplyAsync(() -> {
            UserDao.checkHighscore(score, UserDao.getUsername());
            return null;
        }, GameDatabase.databaseWriterExecutorService);
    }
}
