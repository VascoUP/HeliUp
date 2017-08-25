package com.av.game.logic.object.building;

import com.av.game.logic.Game;
import com.av.game.logic.object.CollidableObject;
import com.av.game.logic.object.GameObject;
import com.av.game.logic.throwable.OccupiedPositionError;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

public class Building extends CollidableObject {
    public Building(Vector2 position) throws OccupiedPositionError {
        super(position, new Polygon(new float[]{0, 0, 0, 706, 58, 780, 62, 780, 119, 706, 119, 0}));
    }

    @Override
    public void onCollision(GameObject object) {
        Game.getGame().endGame();
    }
}
