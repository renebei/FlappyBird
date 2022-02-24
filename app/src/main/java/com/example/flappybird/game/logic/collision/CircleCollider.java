package com.example.flappybird.game.logic.collision;

import com.example.flappybird.game.logic.Position;

public class CircleCollider implements Collider {
    private final float radius;
    private Position position;

    /**
     * @param radius radius de Kreises
     * @param pos position des Kreises
     */
    public CircleCollider(float radius, Position pos) {
        this.radius = radius;
        this.position = pos;
    }

    /**
     * Setzt position auf gegeben Position
     * @param position position an den der Collider gesetzt werden soll
     */
    private void updatePos(Position position){
        this.position = position;
    }

    /**
     * Checkt ob dieser Collider mit einem anderen CircleCollider collidiert
     * @param c CircleCollider mit dem eine colission getestet werden soll
     * @return return if colission appeared
     */
    public boolean collides(CircleCollider c) {
        return Math.sqrt(
                ((position.getX() - c.position.getX()) * (position.getX() - c.position.getX())) +
                        ((position.getY() - c.position.getY()) * (position.getY() - c.position.getY()))
        ) < (getRadius() + getRadius());
    }

    /**
     * Checkt ob dieser Collider mit einem anderen RectangleCollider collidiert
     * @param r RectangleCollider mit dem eine colission getestet werden soll
     * @return return if colission appeared
     */
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

    /**
     * Checkt ob dieser Collider mit einem anderen Collidiert
     * @param c Colider mit dem eine Colission getestet werden soll
     * @return return if colission appeared
     */
    @Override
    public boolean collides(Collider c) {
        if (c instanceof RectangleCollider) return collides((RectangleCollider) c);
        else if (c instanceof CircleCollider) return collides((CircleCollider) c);
        else return false;
    }

    /**
     * Gibt radius des Colliders zurück
     * @return radius des Kreises
     */
    public float getRadius() {
        return radius;
    }

    /**
     * Gibt Position des Colliders zurück
     * @return position des Kreises
     */
    public Position getPosition() {
        return position;
    }
}
