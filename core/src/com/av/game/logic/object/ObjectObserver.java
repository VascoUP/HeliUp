package com.av.game.logic.object;

public interface ObjectObserver {
    void objectCreated(GameObject objectCreated);
    void objectDestroyed(GameObject objectDestroyed);
}
