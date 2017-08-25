package com.av.game.logic.object.helicopter.component.base;

public class CoreHeliBase implements HeliBase {
    private float acceleration_y;
    private float velocity_x;

    public CoreHeliBase() {
        acceleration_y = 1f;
        velocity_x = 5f;
    }

    @Override
    public float getAccelerationY() {
        return acceleration_y;
    }

    @Override
    public float getVelocityX() {
        return velocity_x;
    }

    @Override
    public void setVelocityX(float velocity_x) {
        this.velocity_x = velocity_x;
    }
}
