package com.example.flappybird.profile.history;
//René Beiermann


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "Attempts")
public class Attempt {

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name="history_id")
        private int id;

        @ColumnInfo(name="history_score")
        private int score;
        //will be some sort of datatype to display hours and minutes soon.
        @ColumnInfo(name="history_date")
        private String date;

        public Attempt(int score, String date) {
            this.score = score;
            this.date = date;
        }

        //Getter and Setter for Room
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
