package com.av.game.logic.physics;

import com.av.game.logic.Game;
import com.av.game.logic.object.GameObject;
import com.badlogic.gdx.Gdx;

import java.util.HashSet;
import java.util.Set;

public class CollisionObserver {
    private static String TAG = "CollisionObserver";
    private static CollisionObserver instance;

    private Set<GameObject> collision_objects;
    private Set<Collidable> collidables;

    private Set<GameObject> collisions_to_remove;
    private Set<Collidable> collidables_to_remove;

    private CollisionObserver() {
        collision_objects = new HashSet<GameObject>();
        collidables = new HashSet<Collidable>();
        collisions_to_remove = new HashSet<GameObject>();
        collidables_to_remove = new HashSet<Collidable>();
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
        //instance.collision_objects.remove(collision_object);
    }

    public static void addCollidable(Collidable collidable) {
        instance.collidables.add(collidable);
    }

    public static void removeCollidable(Collidable collidable) {
        instance.collidables_to_remove.add(collidable);
        //instance.collidables.remove(collidable);
    }

    public static void clear() {
        instance.collidables.clear();
    }

    public void checkCollisions() {
        for(GameObject removable : collisions_to_remove)
            collision_objects.remove(removable);
        for(Collidable removable : collidables_to_remove)
            collidables.remove(removable);
        for(GameObject collision_object : collision_objects)
            for(Collidable collidable : collidables)
                if(collidable.isColliding(collision_object))
                    collidable.onCollision(collision_object);
    }
}
