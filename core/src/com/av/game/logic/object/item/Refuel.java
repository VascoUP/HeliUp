package com.av.game.logic.object.item;

import com.av.game.logic.Game;
import com.av.game.logic.object.CollidableObject;
import com.av.game.logic.object.GameObject;
import com.av.game.logic.physics.CollisionObserver;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

public class Refuel extends CollidableObject implements Item {
    public Refuel(Vector2 position) {
        super(position, new Polygon(new float[] {0,0,0,27,23,27,23,0}));
    }

    @Override
    public void takeEffect() {
        Game.getGame().getHelicopter().getHeliFuel().setFuel(Game.getGame().getHelicopter().getHeliFuel().getCapacity());
    }

    @Override
    public void onCollision(GameObject object) {
        takeEffect();
        CollisionObserver.removeCollidable(this);
        Game.getGame().rmItem(this);
    }
}
