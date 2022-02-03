package com.example.flappybird.profile.data;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.concurrent.CompletableFuture;

//IS GOING TO BE USED LATER. WILL RUN IN BACKGROUND THREAD

public class DatabaseAdapter {
    private UserDao Udao;

    public DatabaseAdapter(Application application) {
        UserDatabase Udb = UserDatabase.getInstance(application);
        this.Udao = Udb.UserDao();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public CompletableFuture<User> insertUsername(final String username) {
        return CompletableFuture.supplyAsync(() -> {
            this.Udao.insert(new User(username));
            User userForUsername = this.Udao.getComparedUsername(username);

            return userForUsername;
        }, UserDatabase.databaseWriterExecutorService);
    }

}
