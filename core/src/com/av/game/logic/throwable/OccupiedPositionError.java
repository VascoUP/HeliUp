package com.av.game.logic.throwable;

import com.av.game.logic.object.GameObject;

public class OccupiedPositionError extends Exception {
    private GameObject colliding_object;

    public OccupiedPositionError(GameObject colliding_object) {
        this.colliding_object = colliding_object;
    }

    public GameObject getCollidingObject() {
        return colliding_object;
    }
}
