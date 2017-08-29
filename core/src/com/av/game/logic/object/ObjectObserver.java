package com.av.game.logic.object;

public interface ObjectObserver {
    void objectCreated(GameObject object_created);
    void objectDestroyed(GameObject object_destroyed);
}
