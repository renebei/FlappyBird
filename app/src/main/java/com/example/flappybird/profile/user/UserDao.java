package com.example.flappybird.profile.user;
//René Beiermann

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface UserDao {
        @Insert(onConflict = REPLACE)
        void insert(User user);

        @Delete
        void delete(User user);

        @Update
        void update(User user);

        @Query("SELECT user_name FROM User limit 1")
        String getUsername();

        @Query("SELECT user_id FROM User limit 1")
        int getID();

        @Query("UPDATE User SET user_highscore = :score WHERE user_highscore < :score and user_name = :username")
        void checkHighscore(int score, String username);

        @Query("SELECT user_highscore FROM User WHERE user_name = :username")
        int getHighscore(String username);

        @Query("SELECT * FROM User WHERE user_name = :Username")
        User getComparedUsername(String Username);

        @Query("UPDATE User SET user_gamesPlayed = user_gamesPlayed + 1 WHERE user_name = :Username")
        void UpdateGamesPlayed(String Username);

        @Query("SELECT user_gamesPlayed from User limit 1")
        int getGamesPlayed();
}
