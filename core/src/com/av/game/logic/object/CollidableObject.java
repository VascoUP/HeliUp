package com.av.game.logic.object;

import com.av.game.logic.physics.Collidable;
import com.av.game.logic.physics.CollisionObserver;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

public class CollidableObject extends GameObject implements Collidable {
    public CollidableObject(Vector2 position, Polygon polygon) {
        super(position, polygon);
        CollisionObserver.addCollidable(this);
    }

    @Override
    public void onCollision(GameObject object) {}

    @Override
    public boolean isColliding(GameObject object) {
        return Intersector.overlapConvexPolygons(object.getCollision(), collision);
    }

    @Override
    public void destroy() {
        CollisionObserver.removeCollidable(this);
    }
}
