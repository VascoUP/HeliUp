package com.av.game.logic.object;

import com.av.game.logic.physics.Physics;

import java.awt.geom.Point2D;

public class PhysicsObject extends GameObject {
    private double acceleration_y;
    private double velocity_y;
    private double velocity_x;

    public PhysicsObject(Point2D position) {
        super(position);
        Physics.getInstance().addObject(this);
    }


    public double getAcceleration_y() {
        return acceleration_y;
    }

    public void setAcceleration_y(double acceleration_y) {
        this.acceleration_y = acceleration_y;
    }

    public double getVelocity_y() {
        return velocity_y;
    }

    public void setVelocity_y(double velocity_y) {
        this.velocity_y = velocity_y;
    }

    public double getVelocity_x() {
        return velocity_x;
    }

    public void setVelocity_x(double velocity_x) {
        this.velocity_x = velocity_x;
    }
}
