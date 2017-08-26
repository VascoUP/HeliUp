package com.av.game.logic.object.building;

import com.av.game.logic.Game;
import com.av.game.logic.object.CollidableObject;
import com.av.game.logic.object.GameObject;
import com.av.game.logic.throwable.OccupiedPositionError;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

public class Building extends CollidableObject {
    public Building(Vector2 position) throws OccupiedPositionError {
        super(position, new Polygon(new float[]{2f, 0f, 117f, 0f, 119f, 774f, 119f, 779f, 0f, 779f, 0f, 774f}));
    }

    @Override
    public void onCollision(GameObject object) {
        Game.getGame().endGame();
    }
}
