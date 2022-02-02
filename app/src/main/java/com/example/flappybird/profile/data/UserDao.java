package com.example.flappybird.profile.data;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import com.example.flappybird.profile.data.User;

public interface UserDao {
        @Insert(onConflict = REPLACE)
        void insert(User user);

        @Delete
        void delete(User user);

        @Update
        void update(User user);

        //Here SQL Queries later
}
