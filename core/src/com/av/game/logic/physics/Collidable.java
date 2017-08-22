package com.av.game.logic.physics;

import com.av.game.logic.object.GameObject;

public interface Collidable {
    void onCollision(GameObject object);
    boolean isColliding(GameObject object);
    boolean isOutOfBounds();
}
