package com.example.flappybird.profile.history;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * @author René Beiermann
 * <p>Dieses "data access object" verwaltet die Entität der Versuche.</p>
 */
@Dao
public interface HistoryDao {
    @Insert(onConflict = REPLACE)
    void insert(Attempt attempt);

    @Delete
    void delete(Attempt attempt);

    @Update
    void update(Attempt attempt);

    @Query("SELECT * FROM Attempts")
    List<Attempt> getHistory();
}
