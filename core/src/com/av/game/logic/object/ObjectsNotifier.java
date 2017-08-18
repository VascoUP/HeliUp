package com.av.game.logic.object;

import com.av.game.logic.object.ObjectObserver;
import com.badlogic.gdx.Gdx;

import java.util.HashSet;
import java.util.Set;

public class ObjectsNotifier {
    private static String TAG = "ObjectsNotifier";

    private static ObjectsNotifier instance;

    private Set<ObjectObserver> observers;

    private ObjectsNotifier() {
        observers = new HashSet<ObjectObserver>();
    }

    public static void createInstance() {
        instance = new ObjectsNotifier();
    }

    public static ObjectsNotifier getInstance() {
        return instance;
    }

    public static void addObserver(ObjectObserver observer) {
        instance.observers.add(observer);
    }

    public static void removeObserver(ObjectObserver observer) {
        instance.observers.remove(observer);
    }

    public void notifyCreate(GameObject objectCreated) {
        Gdx.app.log(TAG, "Object created " + objectCreated);
        for(ObjectObserver observer : observers)
            observer.objectCreated(objectCreated);
    }

    public void notifyDestroy(GameObject objectDestroyed) {
        Gdx.app.log(TAG, "Object destroyed " + objectDestroyed);
        for(ObjectObserver observer : observers)
            observer.objectDestroyed(objectDestroyed);
    }
}
