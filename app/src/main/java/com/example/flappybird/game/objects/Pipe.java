package com.example.flappybird.game.objects;

import com.example.flappybird.game.logic.Game;
import com.example.flappybird.game.logic.GameObject;
import com.example.flappybird.game.logic.Position;
import com.example.flappybird.game.logic.collision.RectangleCollider;

public class Pipe extends GameObject {
    private float width;
    private float height;

    public Pipe(Position position, float width, float height) {
        super(position);
        this.height = height;
        this.width = width;
    }

    @Override
    public void update(float frameTime) {
        super.move(Game.SPEED*frameTime, 0);
    }

    public RectangleCollider getCollider() {
        return new RectangleCollider(getPosition(), width, height);
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
