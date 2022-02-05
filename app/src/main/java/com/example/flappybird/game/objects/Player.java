package com.example.flappybird.game.objects;

import com.example.flappybird.game.logic.Game;
import com.example.flappybird.game.logic.GameObject;
import com.example.flappybird.game.logic.Position;
import com.example.flappybird.game.logic.collision.CircleCollider;

public class Player extends GameObject {
    private final float JUMPFORCE = 800.f;

    private boolean isDead;
    private int score;
    private float radius;
    private float force;

    public Player(Position position, float radius) {
        super(position);
        this.radius = radius;
        this.force = 0;
        this.score = 0;
        this.isDead = false;
    }

    @Override
    public void update(float frameTime) {
        // jump up or down
        super.move(0,force * frameTime);

        force += Game.GRAVIATION * frameTime;
    }

    public void jump(){
        if(isDead) return;
        force = -JUMPFORCE;
    }

    public CircleCollider getCollider() {
        return new CircleCollider(radius, getPosition());
    }

    public float getRadius() {
        return radius;
    }

    public void kill(){
        this.isDead = true;
    }

    public boolean isDead() {
        return isDead;
    }

    public void addScore(){
        this.score++;
    }

    public int getScore() {
        return score;
    }
}
