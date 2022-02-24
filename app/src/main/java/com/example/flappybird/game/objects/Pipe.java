package com.example.flappybird.game.objects;

import com.example.flappybird.game.logic.Game;
import com.example.flappybird.game.logic.GameObject;
import com.example.flappybird.game.logic.Position;
import com.example.flappybird.game.logic.collision.RectangleCollider;

/**
 * @author David Siegbert
 * enthalt ein Pipe Game Objekt
 */
public class Pipe extends GameObject {
    private float width;
    private float height;

    /**
     * @param position position der pipe
     * @param width breite der pipe
     * @param height höhe der pipe
     */
    public Pipe(Position position, float width, float height) {
        super(position);
        this.height = height;
        this.width = width;
    }

    /**
     * Bewegt die Pipe nach Links
     * @param frameTime zeit zwischen zwei frames
     */
    @Override
    public void update(float frameTime) {
        super.move(Game.SPEED*frameTime, 0);
    }

    /**
     * @return RectangleCollider von der Pipe
     */
    public RectangleCollider getCollider() {
        return new RectangleCollider(getPosition(), width, height);
    }

    /**
     * @return gibt breite der Pipe
     */
    public float getWidth() {
        return width;
    }

    /**
     * @return gibt höhe der Pipe
     */
    public float getHeight() {
        return height;
    }
}
