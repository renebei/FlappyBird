package com.example.flappybird.profile;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = {@Index(value="user_name", unique = true)})
public class User {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name="user_name")
    private String name;
    @ColumnInfo(name="user_score")
    private int highscore;

    //will be some sort of time datatype soon
    @ColumnInfo(name="user_playtime")
    private int time;

    public User(String name) {
        this.name = name;
    }


    //Getter and Setter for Room.
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHighscore() {
        return highscore;
    }

    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
