package com.example.flappybird.profile.data;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.flappybird.profile.data.User;
@Dao
public interface UserDao {
        @Insert(onConflict = REPLACE)
        void insert(User user);

        @Delete
        void delete(User user);

        @Update
        void update(User user);

        //Here SQL Queries
        @Query("SELECT user_name FROM User limit 1")
        String getUsername();

        @Query("SELECT user_id FROM User limit 1")
        int getID();

        @Query("SELECT * FROM User WHERE user_name = :Username")
        User getComparedUsername(String Username);
}
