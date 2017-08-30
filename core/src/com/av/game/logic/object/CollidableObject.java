package com.av.game.logic.object;

import com.av.game.logic.Game;
import com.av.game.logic.physics.Collidable;
import com.av.game.logic.physics.CollisionNotifier;
import com.av.game.logic.throwable.OccupiedPositionError;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

public class CollidableObject extends GameObject implements Collidable {
    public CollidableObject(Vector2 position, Polygon polygon) throws OccupiedPositionError {
        super(position, polygon);
        CollisionNotifier.addCollidable(this);
    }

    @Override
    public void onCollision(GameObject object) {}

    @Override
    public boolean isColliding(GameObject object) {
        //Check collision based on the collision polygons
        Polygon collision = object.getCollision();
        Polygon collidable = getCollision();
        return Intersector.overlapConvexPolygons(collision, collidable);
    }

    @Override
    public boolean isOutOfBounds() {
        //If object out of bounds destroy it
        if(!Game.getGame().objectOutOfBounds(this))
            return false;
        CollisionNotifier.removeCollidable(this);
        Game.getGame().rmObject(this);
        return true;
    }

    /*@Override
    public void destroy() {
        //On destroy remove this object from collision observer
        CollisionNotifier.removeCollidable(this);
    }*/
}
