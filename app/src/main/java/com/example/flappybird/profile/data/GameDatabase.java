package com.example.flappybird.profile.data;
//René Beiermann

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.flappybird.profile.user.User;
import com.example.flappybird.profile.user.UserDao;
import com.example.flappybird.profile.history.History;
import com.example.flappybird.profile.history.HistoryDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class, History.class}, version = 2, exportSchema = false)
public abstract class GameDatabase extends RoomDatabase {

    private static volatile GameDatabase database;

    private static final String DATABASE_NAME = "userdata";

    static ExecutorService databaseWriterExecutorService =
            Executors.newSingleThreadExecutor();

    public synchronized static GameDatabase getInstance(Context context) {
        if (database == null) {
            //if null init
            database = Room.databaseBuilder(context.getApplicationContext()
                    , GameDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }

    public abstract UserDao UserDao();

    public abstract HistoryDao HistoryDao();
}
