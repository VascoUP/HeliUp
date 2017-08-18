package com.av.game.logic.object;

import com.av.game.logic.physics.Collidable;
import com.av.game.logic.physics.CollisionObserver;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.StringBuilder;

public class CollidableObject extends GameObject implements Collidable {
    private static String TAG = "CollidableObject";

    public CollidableObject(Vector2 position, Polygon polygon) {
        super(position, polygon);
        CollisionObserver.addCollidable(this);
    }

    @Override
    public void onCollision(GameObject object) {}

    @Override
    public boolean isColliding(GameObject object) {
        Polygon collision = object.getCollision();
        Polygon collidable = getCollision();
        return Intersector.overlapConvexPolygons(collision, collidable);
    }

    @Override
    public void destroy() {
        CollisionObserver.removeCollidable(this);
    }
}
