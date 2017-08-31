package com.av.game.logic.physics;

import com.av.game.logic.object.GameObject;
import com.av.game.logic.object.ObjectObserver;
import com.av.game.logic.throwable.OccupiedPositionError;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class CollisionNotifier implements ObjectObserver {
    private static String TAG = CollisionNotifier.class.getSimpleName();

    //Singleton class: only one instance of this class is allowed to exist
    private static CollisionNotifier instance;

    //Objects that might crash into other objects
    private Set<GameObject> collision_objects;
    //Static objects that are only crashed onto, and never crash on their own
    private Set<Collidable> collidables;

    //Queue of objects that will be destroyed in the next update
    private Queue<GameObject> collisions_to_remove;
    private Queue<Collidable> collidables_to_remove;

    private CollisionNotifier() {
        collision_objects = new HashSet<GameObject>();
        collidables = new HashSet<Collidable>();
        collisions_to_remove = new PriorityQueue<GameObject>();
        collidables_to_remove = new PriorityQueue<Collidable>();
    }

    public static void createInstance() {
        instance = new CollisionNotifier();
    }

    public static CollisionNotifier getInstance() {
        return instance;
    }

    public static void clear() {
        instance.collision_objects.clear();
        instance.collidables.clear();
    }

    public static void addCollisionObject(GameObject collision_object) {
        instance.collision_objects.add(collision_object);
    }

    public static void removeCollisionObject(GameObject collision_object) {
        instance.collisions_to_remove.add(collision_object);
    }

    public static boolean addCollidable(Collidable collidable) throws OccupiedPositionError {
        for(Collidable comp : instance.collidables)
            if(collidable.isColliding((GameObject)comp))
                throw new OccupiedPositionError((GameObject)collidable);
        instance.collidables.add(collidable);
        return true;
    }

    public static void removeCollidable(Collidable collidable) {
        instance.collidables_to_remove.add(collidable);
    }

    public void checkCollisions() {
        //Remove objects on the queues
        while(!collisions_to_remove.isEmpty())
            collision_objects.remove(collisions_to_remove.remove());
        while(!collidables_to_remove.isEmpty())
            collidables.remove(collidables_to_remove.remove());

        //Check collisions
        for(GameObject collision_object : collision_objects)
            for(Collidable collidable : collidables) {
                if (collidable.isColliding(collision_object))
                    collidable.onCollision(collision_object);
                else
                    collidable.isOutOfBounds();
            }
    }

    @Override
    public void objectCreated(GameObject object_created) {}

    @Override
    public void objectDestroyed(GameObject object_destroyed) {
        removeCollisionObject(object_destroyed);
        if(object_destroyed.getClass().isAssignableFrom(Collidable.class))
            removeCollidable((Collidable)object_destroyed);
    }
}
