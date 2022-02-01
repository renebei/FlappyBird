package com.example.flappybird.gamelogic;

public class CircleCollider implements Collider {
    private final float radius;
    private Position position;

    public CircleCollider(int radius, Position pos) {
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

    public boolean collides(RectangleCollider c) {
        //check for x collision
        return ((getPosition().getX() > c.getPosition().getX() && getPosition().getX() < c.getPosition().getX() + c.getWidth())
                || (getPosition().getX() + radius > c.getPosition().getX() && getPosition().getX() + radius < c.getPosition().getX() + c.getWidth())
                || (getPosition().getX() - radius > c.getPosition().getX() && getPosition().getX() - radius < c.getPosition().getX() + c.getWidth())
                // if ball to big <3
                || (getPosition().getX() - radius < c.getPosition().getX() && getPosition().getX() + radius > c.getPosition().getX() + c.getWidth())
        ) &&
                //check for y collision
                ((getPosition().getY() > c.getPosition().getY() && getPosition().getY() < c.getPosition().getY() + c.getWidth())
                        || (getPosition().getY() + radius > c.getPosition().getY() && getPosition().getY() + radius < c.getPosition().getY() + c.getWidth())
                        || (getPosition().getY() - radius > c.getPosition().getY() && getPosition().getY() - radius < c.getPosition().getY() + c.getWidth())
                        // if ball to big <3
                        || (getPosition().getY() - radius < c.getPosition().getY() && getPosition().getY() + radius > c.getPosition().getY() + c.getWidth())
                );
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
