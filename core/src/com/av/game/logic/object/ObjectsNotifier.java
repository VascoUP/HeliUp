package com.av.game.logic.object;

import java.util.HashSet;
import java.util.Set;

public class ObjectsNotifier {
    //Singleton class: only one instance of this class is allowed to exist
    private static ObjectsNotifier instance;

    //Set of observer that will be notified
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

    public static void clear() {
        instance.observers.clear();
    }

    public static void addObserver(ObjectObserver observer) {
        instance.observers.add(observer);
    }

    public static void removeObserver(ObjectObserver observer) {
        instance.observers.remove(observer);
    }

    public void notifyCreate(GameObject objectCreated) {
        //Notify all observer if an object was created
        for(ObjectObserver observer : observers)
            observer.objectCreated(objectCreated);
    }

    public void notifyDestroy(GameObject objectDestroyed) {
        //Notify all observer if an object was destroyed
        for(ObjectObserver observer : observers)
            observer.objectDestroyed(objectDestroyed);
    }
}
