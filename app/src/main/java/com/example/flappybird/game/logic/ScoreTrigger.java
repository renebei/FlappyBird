package com.example.flappybird.game.logic;

import com.example.flappybird.game.logic.collision.RectangleCollider;


/**
 * @author David Siegbert
 * Diese Klasse enthält einen Trigger, welcher den Score bei Interaktion mit dem Spieler den Score um einen Hochzählt
 */
public class ScoreTrigger extends GameObject {
    private float width;
    private float height;
    private boolean hasTriggered;

    /**
     * @param position spawnposition des Triggers
     * @param width Breite des Triggers
     * @param height Spawnpunkt des Triggers
     */
    public ScoreTrigger(Position position, float width, float height) {
        super(position);
        this.height = height;
        this.width = width;

    }

    /**
     * Bewege den ScoreTrigger nach Links
     * @param frameTime zeit die zwischen diesem und dem letzten Frame vergangen ist
     */
    @Override
    public void update(float frameTime) {
        super.move(Game.SPEED*frameTime, 0);
    }

    public RectangleCollider getCollider() {
        return new RectangleCollider(getPosition(), width, height);
    }

    /**
     * Gibt zurück ob der trigger schon aktiviert wurde
     * @return gibt zurück ob der trigger schon aktiviert wurde
     */
    public boolean getHasTriggered() {
        return hasTriggered;
    }

    /**
     * Setze den Trigger
     * @param hasTriggered setter if the trigger has been triggered
     */
    public void setHasTriggered(boolean hasTriggered) {
        this.hasTriggered = hasTriggered;
    }

}
