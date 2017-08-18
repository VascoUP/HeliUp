package com.av.game.logic.physics;

import com.av.game.logic.object.GameObject;
import com.av.game.logic.object.PhysicsObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import java.awt.geom.Point2D;
import java.util.LinkedList;

public class Physics {
    private static String TAG = "Physics";
    public static Physics instance;

    private final float GRAVITY_ACCELERATION = 0.5f;

    private LinkedList<PhysicsObject> objects;

    private Physics() {
        objects = new LinkedList<PhysicsObject>();
    }

    public static void createInstance() {
        instance = new Physics();
    }

    public static Physics getInstance() {
        return instance;
    }

    public static void addObject(PhysicsObject object) {
        instance.objects.add(object);
    }

    public static void rmObject(PhysicsObject object) {
        instance.objects.remove(object);
    }

    public static void clear() {
        instance.objects.clear();
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

}
