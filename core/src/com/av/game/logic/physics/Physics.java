package com.av.game.logic.physics;

import com.av.game.logic.object.GameObject;
import com.av.game.logic.object.PhysicsObject;
import com.badlogic.gdx.math.Vector2;

import java.awt.geom.Point2D;
import java.util.LinkedList;

public class Physics {
    public static Physics physics;

    private final float GRAVITY_ACCELERATION = 0.5f;

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
            Vector2 position = object.getPosition();
            float x = object.getVelocity_x() + position.x;
            object.setVelocity_y(object.getVelocity_y() + object.getAcceleration_y() - GRAVITY_ACCELERATION);
            float y = object.getVelocity_y() + position.y;
            position.set(x, y);
        }
    }

    public void clear() {
        objects.clear();
    }
}
