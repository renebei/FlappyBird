package com.example.flappybird.profile.history;
//René Beiermann


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.flappybird.profile.user.User;

@Entity(tableName = "Attempts")
public class Attempt {
// foreignKeys = {@ForeignKey(entity = User.class, parentColumns = "user_id", childColumns = "attempt_user_id", onDelete = ForeignKey.CASCADE)}


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "attempt_id")
    private int id;

    @ColumnInfo(name = "attempt_score")
    private int score;

    @ColumnInfo(name = "attempt_date")
    private String date;

    @ColumnInfo(name = "attempt_user_id")
    private int userId;


    public Attempt(int id, int score, String date) {
        this.score = score;
        this.date = date;
        this.id = id;
    }

    //Getter and Setter for Room

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
