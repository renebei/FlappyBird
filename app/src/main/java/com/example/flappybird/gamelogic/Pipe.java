package com.example.flappybird.gamelogic;

public class Pipe extends GameObject{
    private float width;
    private float height;
    private RectangleCollider collider;

    public Pipe(Position position, float width, float height, float speed) {
        super(position);
        collider = new RectangleCollider(position, width, height);
        this.height = height;
        this.width = width;

    }

    @Override
    public void update(float frameTime) {
        super.move(Game.SPEED*frameTime, 0);
    }

    public RectangleCollider getCollider() {
        return collider;
    }
}
