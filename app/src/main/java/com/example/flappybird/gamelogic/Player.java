package com.example.flappybird.gamelogic;

public class Player extends GameObject{
    private final float JUMPFORCE = 5.f;

    private float radius;
    private CircleCollider collider;
    private float force;

    public Player(Position position, float radius, CircleCollider collider, float force) {
        super(position);
        this.radius = radius;
        this.collider = collider;
        this.force = force;
    }

    @Override
    public void update(float frameTime) {
        // jump up or down
        super.move(0,force * frameTime);
        force -= Game.GRAVIATION * frameTime;
    }

    public void jump(){
        force = JUMPFORCE;
    }

    public CircleCollider getCollider() {
        return collider;
    }
}
