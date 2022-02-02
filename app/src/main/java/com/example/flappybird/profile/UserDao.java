package com.example.flappybird.profile;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

public interface UserDao {
        @Insert(onConflict = REPLACE)
        void insert(User user);

        @Delete
        void delete(User user);

        @Update
        void update(User user);

        //Here SQL Queries later
}
