package com.example.flappybird.game.objects;

import com.example.flappybird.game.logic.Game;
import com.example.flappybird.game.logic.GameObject;
import com.example.flappybird.game.logic.Position;
import com.example.flappybird.game.logic.collision.CircleCollider;

/**
 * @author David Siegbert
 * Spieler, mit Sprung und gravität
 */
public class Player extends GameObject {
    private final float JUMPFORCE = 800.f;

    private boolean isDead;
    private int score;
    private float radius;
    private float force;

    /**
     * @param position position des Spielerns
     * @param radius radius des Spielers
     */
    public Player(Position position, float radius) {
        super(position);
        this.radius = radius;
        this.force = 0;
        this.score = 0;
        this.isDead = false;
    }

    /**
     * Wirkt gravität
     * @param frameTime zeit zwischen zwei frames
     */
    @Override
    public void update(float frameTime) {
        // jump up or down
        super.move(0,force * frameTime);

        force += Game.GRAVIATION * frameTime;
    }

    /**
     * Lösst einen Sprung des Spielers aus
     */
    public void jump(){
        if(isDead) return;
        force = -JUMPFORCE;
    }

    /**
     * @return Collider des Spielers als CircleColliders
     */
    public CircleCollider getCollider() {
        return new CircleCollider(radius, getPosition());
    }

    /**
     * @return den Radius des Spielers
     */
    public float getRadius() {
        return radius;
    }

    /**
     * "tötet" den spieler
     */
    public void kill(){
        this.isDead = true;
    }

    /**
     * @return gibt zurück, ob der Spieler tot ist
     */
    public boolean isDead() {
        return isDead;
    }

    /**
     * fügt einen Score hinzu
     */
    public void addScore(){
        this.score++;
    }

    /**
     * @return gibt den derzeitiegene Score zurück
     */
    public int getScore() {
        return score;
    }
}
