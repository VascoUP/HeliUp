package com.av.game.logic.physics;

import com.av.game.logic.object.PhysicsObject;

import java.awt.geom.Point2D;
import java.util.LinkedList;

public class Physics {
    public static Physics physics;

    private LinkedList<PhysicsObject> objects;

    private Physics() {
        objects = new LinkedList<PhysicsObject>();
    }

    public static void createInstance() {
        physics = new Physics();
    }

    public static Physics getInstance() {
        return physics;
    }

    public void addObject(PhysicsObject object) {
        objects.add(object);
    }

    public void rmObject(PhysicsObject object) {
        objects.remove(object);
    }

    public void update() {
        for(PhysicsObject object : objects) {
            Point2D position = object.getPosition();
            double x = object.getVelocity_x() + position.getX();
            object.setVelocity_y(object.getVelocity_y() + object.getAcceleration_y());
            double y = object.getVelocity_y() + position.getX();
            position.setLocation(x, y);
        }
    }

    public void clear() {
        objects.clear();
    }
}
