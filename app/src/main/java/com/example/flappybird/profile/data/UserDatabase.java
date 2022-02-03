package com.example.flappybird.profile.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.flappybird.history.History;
import com.example.flappybird.history.HistoryDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class, History.class}, version =2, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {

    private static volatile UserDatabase database;

    private static final String DATABASE_NAME = "userdata";

    static ExecutorService databaseWriterExecutorService =
            Executors.newSingleThreadExecutor();

    public synchronized static UserDatabase getInstance(Context context) {
        if (database == null) {
            //if null init
            database = Room.databaseBuilder(context.getApplicationContext()
                    , UserDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }

    public abstract UserDao UserDao();

    public abstract HistoryDao HistoryDao();
}
