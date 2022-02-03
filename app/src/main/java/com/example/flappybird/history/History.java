package com.example.flappybird.history;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "history")
public class History {

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name="history_id")
        private int id;
        @ColumnInfo(name="history_score")
        private int score;

        //will be some sort of datatype to display hours and minutes soon.
        @ColumnInfo(name="history_date")
        private int date;

        public History(int score, int date) {
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

        public int getDate() {
                return date;
        }

        public void setDate(int date) {
                this.date = date;
        }
}
