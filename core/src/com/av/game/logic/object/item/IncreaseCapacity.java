package com.av.game.logic.object.item;

import com.av.game.logic.Game;
import com.av.game.logic.object.CollidableObject;
import com.av.game.logic.object.GameObject;
import com.av.game.logic.object.helicopter.Helicopter;
import com.av.game.logic.object.helicopter.component.fuel.IncreaseFuelCapacity;
import com.av.game.logic.physics.CollisionObserver;
import com.av.game.logic.throwable.OccupiedPositionError;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

public class IncreaseCapacity extends CollidableObject implements Item {
    public IncreaseCapacity(Vector2 position) throws OccupiedPositionError {
        super(position, new Polygon(new float[] {0,0,0,27,23,27,23,0}));
    }

    @Override
    public void takeEffect() {
        Helicopter helicopter = Game.getGame().getHelicopter();
        helicopter.setHeliFuel(new IncreaseFuelCapacity(helicopter.getHeliFuel()));
    }

    @Override
    public void onCollision(GameObject object) {
        takeEffect();
        CollisionObserver.removeCollidable(this);
        Game.getGame().rmItem(this);
    }
}
