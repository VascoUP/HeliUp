package com.av.game.logic.object;

import com.av.game.logic.physics.Physics;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

public class PhysicsObject extends GameObject {
    private float acceleration_y;
    private float velocity_y;
    protected float velocity_x;

    public PhysicsObject(Vector2 position, Polygon polygon) {
        super(position, polygon);
        Physics.addObject(this);
    }

    public PhysicsObject(Vector2 position, float rotation, Polygon polygon) {
        super(position, rotation, polygon);
        Physics.addObject(this);
    }

    public float getAcceleration_y() {
        return acceleration_y;
    }

    public void setAcceleration_y(float acceleration_y) {
        this.acceleration_y = acceleration_y;
    }

    public float getVelocity_y() {
        return velocity_y;
    }

    public void setVelocity_y(float velocity_y) {
        this.velocity_y = velocity_y;
    }

    public float getVelocity_x() {
        return velocity_x;
    }

    public void setVelocity_x(float velocity_x) {
        this.velocity_x = velocity_x;
    }

    public void destroy() {
        Physics.rmObject(this);
    }
}
