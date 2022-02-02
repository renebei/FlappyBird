package com.example.flappybird.profile.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {UserDatabase.class}, version = 1, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {

    private static UserDatabase database;

    private static final String DATABASE_NAME = "userdata";

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
}
