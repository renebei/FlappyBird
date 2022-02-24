package com.example.flappybird.game.logic;

/**
 * @author David Siegbert
 * Gibt eine Position an und verschiedene Zugriffsmöglichkeiten auf diese
 */
public class Position {
    private float x;
    private float y;

    /**
     * @param x x position
     * @param y y position
     */
    public Position(float x, float y){
        this.x = x;
        this.y = y;
    }

    /**
     * Setze Position
     * @param x x position
     * @param y y position
     */
    public void setPosition(float x, float y){
        this.x = x;
        this.y = y;
    }

    /**
     * Bewege derzeitiege Person
     * @param x x position
     * @param y y position
     */
    public void movePosition(float x, float y){
        this.x += x;
        this.y += y;
    }

    /**
     * Gebe derzeitiege x koordinate zurück
     * @return derzeitiege x koordinate
     */
    public float getX() {
        return x;
    }

    /**
     * Gebe derzeitiege y koordinate zurück
     * @return derzeitiege y koordinate
     */
    public float getY() {
        return y;
    }

    /**
     * Füge eine position zu der derzeitiegen hinzu
     * @param x x position, der auf die derzeitiege hinzugefügt wird
     * @param y y position, der auf die derzeitiege hinzugefügt wird
     */
    public Position addPosition(float x, float y){
        return new Position(this.x + x, this.y + y);
    }
}
