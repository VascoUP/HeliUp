package com.av.game.logic.physics;

import com.av.game.logic.object.GameObject;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class CollisionObserver {
    private static String TAG = "CollisionObserver";
    private static CollisionObserver instance;

    private Set<GameObject> collision_objects;
    private Set<Collidable> collidables;

    private Queue<GameObject> collisions_to_remove;
    private Queue<Collidable> collidables_to_remove;

    private CollisionObserver() {
        collision_objects = new HashSet<GameObject>();
        collidables = new HashSet<Collidable>();
        collisions_to_remove = new PriorityQueue<GameObject>();
        collidables_to_remove = new PriorityQueue<Collidable>();
    }

    public static void createInstance() {
        instance = new CollisionObserver();
    }

    public static CollisionObserver getInstance() {
        return instance;
    }

    public static void addCollisionObject(GameObject collision_object) {
        instance.collision_objects.add(collision_object);
    }

    public static void removeCollisionObject(GameObject collision_object) {
        instance.collisions_to_remove.add(collision_object);
    }

    public static void addCollidable(Collidable collidable) {
        instance.collidables.add(collidable);
    }

    public static void removeCollidable(Collidable collidable) {
        instance.collidables_to_remove.add(collidable);
    }

    public static void clear() {
        instance.collidables.clear();
    }

    public void checkCollisions() {
        while(!collisions_to_remove.isEmpty())
            collision_objects.remove(collisions_to_remove.remove());
        while(!collidables_to_remove.isEmpty())
            collidables.remove(collidables_to_remove.remove());
        for(GameObject collision_object : collision_objects)
            for(Collidable collidable : collidables)
                if(collidable.isColliding(collision_object))
                    collidable.onCollision(collision_object);
    }
}
