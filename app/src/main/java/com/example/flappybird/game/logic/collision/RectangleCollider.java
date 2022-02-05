package com.example.flappybird.game.logic.collision;

import com.example.flappybird.game.logic.Position;

public class RectangleCollider implements Collider {

    private Position position;
    private float width;
    private float height;

    public RectangleCollider(Position position, float width, float height) {
        this.position = position;
        this.width = width;
        this.height = height;
    }

    private void updatePos(Position position){
        this.position = position;
    }

    public boolean collides(CircleCollider c) {
        // not used for now
        return false;
    }

    public boolean collides(RectangleCollider c){
        // not used for now
        return false;
    }

    @Override
    public boolean collides(Collider c) {
        if(c instanceof RectangleCollider) return collides((RectangleCollider) c);
        else if(c instanceof CircleCollider) return collides((CircleCollider) c);
        else return false;
    }

    public Position getPosition() {
        return position;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
