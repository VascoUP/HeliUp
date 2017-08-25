package com.av.game.logic.physics;

import com.av.game.logic.object.PhysicsObject;
import com.badlogic.gdx.math.Vector2;

import java.util.LinkedList;

public class Physics {
    private static String TAG = "Physics";
    public static Physics instance;

    public static final float GRAVITY_ACCELERATION = 0.5f;

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
            float x = object.getVelocityX() + position.x;
            object.setVelocityY(object.getVelocityY() + object.getAccelerationY() - GRAVITY_ACCELERATION);
            float y = object.getVelocityY() + position.y;
            position.set(x, y);
        }
    }

}
