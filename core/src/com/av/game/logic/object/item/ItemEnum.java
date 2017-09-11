package com.av.game.logic.object.item;

import com.av.game.logic.Game;
import com.av.game.logic.object.GameObject;
import com.av.game.logic.object.helicopter.Helicopter;
import com.av.game.logic.throwable.OccupiedPositionError;
import com.av.game.random.RandomFunctions;
import com.av.game.screen.util.ScreenInfo;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public enum ItemEnum {
    UP_VELOCITY {
        @Override
        GameObject getItem(Random random, float max, float min, Object... params) throws OccupiedPositionError {
            Helicopter helicopter = Game.getGame().getHelicopter();
            return new IncreaseVelocity(new Vector2(helicopter.getPosition().x + ScreenInfo.width - 200f, RandomFunctions.nextFloat(random, max, min)));
        }
    }, UP_CAPACITY {
        @Override
        GameObject getItem(Random random, float max, float min, Object... params) throws OccupiedPositionError {
            Helicopter helicopter = Game.getGame().getHelicopter();
            return new IncreaseCapacity(new Vector2(helicopter.getPosition().x + ScreenInfo.width - 200f, RandomFunctions.nextFloat(random, max, min)));
        }
    };

    abstract GameObject getItem(Random random, float max, float min, Object... params) throws OccupiedPositionError;

    public static GameObject randomItem(Random random, float max, float min) throws OccupiedPositionError {
        //Select a random item to spawn
        switch (random.nextInt(2)) {
            case 0:
                //Spawn an increase velocity item
                return UP_VELOCITY.getItem(random, max, min);
            case 1:
                //Spawn an increase capacity item
                return UP_CAPACITY.getItem(random, max, min);
        }

        //Never gets here
        return null;

    }
}
