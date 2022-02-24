package com.example.flappybird.game.logic.collision;

import com.example.flappybird.game.logic.Position;

/**
 * @author David Siegbert
 * Rechteckiger collider
 */
public class RectangleCollider implements Collider {

    private Position position;
    private float width;
    private float height;

    /**
     * @param position position des Colliders
     * @param width Breite des Colliders
     * @param height Höhe des Colliders
     */
    public RectangleCollider(Position position, float width, float height) {
        this.position = position;
        this.width = width;
        this.height = height;
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
        // not used for now
        return false;
    }

    /**
     * Checkt ob dieser Collider mit einem anderen RectangleCollider collidiert
     * @param r RectangleCollider mit dem eine colission getestet werden soll
     * @return return if colission appeared
     */
    public boolean collides(RectangleCollider r){
        // not used for now
        return false;
    }


    /**
     * Checkt ob dieser Collider mit einem anderen Collidiert
     * @param c Colider mit dem eine Colission getestet werden soll
     * @return return if colission appeared
     */
    @Override
    public boolean collides(Collider c) {
        if(c instanceof RectangleCollider) return collides((RectangleCollider) c);
        else if(c instanceof CircleCollider) return collides((CircleCollider) c);
        else return false;
    }

    /**
     * Gibt Position des Colliders zurück
     * @return Position des Rechtecks
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Gibt breite des Colliders zurück
     * @return Breite des Rechtecks
     */
    public float getWidth() {
        return width;
    }

    /**
     * Gibt höhe des Colliders zurück
     * @return höhe des Rechtecks
     */
    public float getHeight() {
        return height;
    }
}
