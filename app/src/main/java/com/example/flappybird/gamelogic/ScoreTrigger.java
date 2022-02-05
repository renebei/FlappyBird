package com.example.flappybird.gamelogic;

public class ScoreTrigger extends GameObject {
    private float width;
    private float height;
    private boolean hasTriggered;

    public ScoreTrigger(Position position, float width, float height) {
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

    public boolean getHasTriggered() {
        return hasTriggered;
    }

    public void setHasTriggered(boolean hasTriggered) {
        this.hasTriggered = hasTriggered;
    }

}
