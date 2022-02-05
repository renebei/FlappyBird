package com.example.flappybird.game.logic;

public abstract class GameObject {
    private Position position;

    public GameObject(Position position){
        this.position = position;
    }

    public void move(float x, float y){
        this.position.movePosition(x,y);
    }

    public void setPosition(float x, float y){
        this.position.setPosition(x,y);
    }

    public Position getPosition() {
        return position;
    }

    public abstract void update(float frameTime);
}
