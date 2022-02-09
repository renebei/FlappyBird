package com.example.flappybird.profile.history;
//René Beiermann

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface HistoryDao {
    @Insert(onConflict = REPLACE)
    void insert(Attempt attempt);

    @Delete
    void delete(Attempt attempt);

    @Update
    void update(Attempt attempt);

    @Query("SELECT * FROM Attempts")
    List<Attempt> getLastFiveMatches();
}
