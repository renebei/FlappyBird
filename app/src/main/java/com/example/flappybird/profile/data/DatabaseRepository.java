package com.example.flappybird.profile.data;

import android.app.Application;

import com.example.flappybird.profile.history.Attempt;
import com.example.flappybird.profile.user.User;
import com.example.flappybird.profile.history.HistoryDao;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author René Beiermann
 * Datenbanken Repository für die Abfragen unserer Anwendung.
 */
public class DatabaseRepository {
    private com.example.flappybird.profile.user.UserDao UserDao;
    private HistoryDao HistoryDao;

    /**
     * @param application Nimmt Unterklasse von Context.
     * <p>Initialisiert Repository</p>
     */
    public DatabaseRepository(Application application) {
        GameDatabase Udb = GameDatabase.getInstance(application);
        this.UserDao = Udb.UserDao();
        this.HistoryDao = Udb.HistoryDao();
    }

    /**
     * @param username Der Nutzername, der in die Datenbank eingetragen wird.
     */
    public CompletableFuture<Void> insertUsername(final String username) {
        return CompletableFuture.supplyAsync(() -> {
            this.UserDao.insert(new User(username));
            return null;
        }, GameDatabase.databaseWriterExecutorService);
    }

    /**
     * @return Nutzername.
     */
    public CompletableFuture<String> getUsername() {
        return CompletableFuture.supplyAsync(() -> {
            String user = this.UserDao.getUsername();
            return user;
        }, GameDatabase.databaseWriterExecutorService);
    }

    /**
     * @param score Nutzer ist n-mal dem Hindernis ausgewichen.
     * @param time Localtime des Versuch formatiert als String.
     */
    public CompletableFuture<Void> addAttempt(int score, String time) {
        return CompletableFuture.supplyAsync(() ->{
            Attempt attempt = new Attempt(score, time);
            this.HistoryDao.insert(attempt);
            return null;
        }, GameDatabase.databaseWriterExecutorService);
    }

    /**
     * @return Die Einträge (List) der Match History.
     */
    public CompletableFuture<List<Attempt>> getHistory() {
        return CompletableFuture.supplyAsync(() ->{
            List<Attempt> attempt = HistoryDao.getHistory();
            return attempt;
        }, GameDatabase.databaseWriterExecutorService);
    }

    /**
     * Update Anzahl der gespielten Spiele.
     */
    public CompletableFuture<Void> incrementGamesPlayed() {
        return CompletableFuture.supplyAsync(() -> {
            UserDao.UpdateGamesPlayed(UserDao.getUsername());
            return null;
        }, GameDatabase.databaseWriterExecutorService);
    }

    /**
     * @return Anzahl (Integer) der gespielten Spiele.
     */
    public CompletableFuture<Integer> getGamesPlayed() {
        return CompletableFuture.supplyAsync(() -> {
            return UserDao.getGamesPlayed();
        }, GameDatabase.databaseWriterExecutorService);
    }

    /**
     * @return Highscore (Integer) des Spieler.
     */
    public CompletableFuture<Integer> getHighscore() {
        return CompletableFuture.supplyAsync(() -> {
           return UserDao.getHighscore(UserDao.getUsername());
        }, GameDatabase.databaseWriterExecutorService);
    }

    /**
     * @param score Wenn ein Spiel vorbei ist, übergib den Score und schau ob dieser der Höchste ist.
     */
    public CompletableFuture<Void> updateHighscore(int score) {
        return CompletableFuture.supplyAsync(() -> {
            UserDao.checkHighscore(score, UserDao.getUsername());
            return null;
        }, GameDatabase.databaseWriterExecutorService);
    }
}
