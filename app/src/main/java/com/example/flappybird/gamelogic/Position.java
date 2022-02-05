package com.example.flappybird.gamelogic;

public class Position {
    private float x;
    private float y;

    public Position(float x, float y){
        this.x = x;
        this.y = y;
    }

    public void setPosition(float x, float y){
        this.x = x;
        this.y = y;
    }

    public void movePosition(float x, float y){
        this.x += x;
        this.y += y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Position addPosition(float x, float y){
        return new Position(this.x + x, this.y + y);
    }
}
