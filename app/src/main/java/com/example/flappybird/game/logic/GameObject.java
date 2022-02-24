package com.example.flappybird.game.logic;

/**
 * @author David Siegbert
 * Oberklasse für ein Gameobjekt
 */
public abstract class GameObject {
    private Position position;

    /**
     * @param position position des game Objektes
     */
    public GameObject(Position position){
        this.position = position;
    }

    /**
     * Bewege das game Objekt
     * @param x wie weit sich das game Objekts auf der x achse bewegen soll
     * @param y wie weit sich das game Objekts auf der y achse bewegen soll
     */
    public void move(float x, float y){
        this.position.movePosition(x,y);
    }

    /**
     * Setze die position des game Objektes auf eine neue positiotn
     *
     * @param x x position
     * @param y y position
     */
    public void setPosition(float x, float y){
        this.position.setPosition(x,y);
    }

    /**
     * Gibt die Position des game Objketes zurück
     * @return die position des game  Objektes
     */
    public Position getPosition() {
        return position;
    }

    /**
     * update sollte jeden frame aufgerufen werden
     * @param frameTime zeit zwischen zwei frames
     */
    public abstract void update(float frameTime);
}
