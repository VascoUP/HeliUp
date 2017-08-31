package com.av.game.logic.physics;

import com.av.game.logic.object.GameObject;
import com.av.game.logic.object.ObjectObserver;
import com.av.game.logic.object.PhysicsObject;
import com.badlogic.gdx.math.Vector2;

import java.util.HashSet;
import java.util.Set;

public class Physics implements ObjectObserver {
    private static String TAG = Physics.class.getSimpleName();

    //Singleton class: only one instance of this class is allowed to exist
    public static Physics instance;

    private static final float GRAVITY_ACCELERATION = 0.8f;

    //List of object affected by physics
    private Set<PhysicsObject> objects;

    private Physics() {
        objects = new HashSet<PhysicsObject>();
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

    @Override
    public void objectCreated(GameObject object_created) {}

    @Override
    public void objectDestroyed(GameObject object_destroyed) {
        if(object_destroyed.getClass().isAssignableFrom(PhysicsObject.class))
            rmObject((PhysicsObject)object_destroyed);
    }
}
