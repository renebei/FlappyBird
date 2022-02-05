package com.example.flappybird.game.logic.collision;

import com.example.flappybird.game.logic.Position;

public class CircleCollider implements Collider {
    private final float radius;
    private Position position;

    public CircleCollider(float radius, Position pos) {
        this.radius = radius;
        this.position = pos;
    }

    private void updatePos(Position position){
        this.position = position;
    }

    public boolean collides(CircleCollider c) {
        return Math.sqrt(
                ((position.getX() - c.position.getX()) * (position.getX() - c.position.getX())) +
                        ((position.getY() - c.position.getY()) * (position.getY() - c.position.getY()))
        ) < (getRadius() + getRadius());
    }

    public boolean collides(RectangleCollider r) {
        // https://stackoverflow.com/questions/21089959/detecting-collision-of-rectangle-with-circle
        float distX = Math.abs(position.getX() - r.getPosition().getX()- r.getWidth()/2);
        float distY = Math.abs(position.getY() - r.getPosition().getY() -r.getHeight()/2);

        if (distX > (r.getWidth()/2 + this.getRadius())) { return false; }
        if (distY > (r.getHeight()/2 + this.getRadius())) { return false; }

        if (distX <= (r.getWidth()/2)) { return true; }
        if (distY <= (r.getHeight()/2)) { return true; }

        float dx=distX-r.getWidth()/2;
        float dy=distY-r.getHeight()/2;
        return (dx*dx+dy*dy<=(this.getRadius()*this.getRadius()));
    }

    @Override
    public boolean collides(Collider c) {
        if (c instanceof RectangleCollider) return collides((RectangleCollider) c);
        else if (c instanceof CircleCollider) return collides((CircleCollider) c);
        else return false;
    }

    public float getRadius() {
        return radius;
    }

    public Position getPosition() {
        return position;
    }
}
