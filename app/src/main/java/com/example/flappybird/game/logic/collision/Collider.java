package com.example.flappybird.game.logic.collision;

/**
 * @author David Siegbert
 * Collider interface
 */
public interface Collider {
    boolean collides(Collider c);
}
