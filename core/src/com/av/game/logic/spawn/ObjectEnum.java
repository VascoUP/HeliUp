package com.av.game.logic.spawn;

import com.av.game.logic.Game;
import com.av.game.logic.object.GameObject;
import com.av.game.logic.object.building.Building;
import com.av.game.logic.object.helicopter.Helicopter;
import com.av.game.logic.object.item.ItemEnum;
import com.av.game.logic.object.item.Refuel;
import com.av.game.logic.throwable.OccupiedPositionError;
import com.av.game.screen.util.ScreenInfo;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public enum ObjectEnum {
    REFUEL {
        @Override
        public GameObject getObject(Random random, float max, float min, Object... params) throws OccupiedPositionError {
            Helicopter helicopter = Game.getGame().getHelicopter();
            return new Refuel(new Vector2(helicopter.getPosition().x + ScreenInfo.width - 200f, random.nextFloat() * max + min));
        }

    }, RANDOM_ITEM {
        @Override
        public GameObject getObject(Random random, float max, float min, Object... params) throws OccupiedPositionError {
            //Random item requires as many possibilities
            return ItemEnum.randomItem(random, max, min);
        }
    }, BUILDING {
        @Override
        public GameObject getObject(Random random, float max, float min, Object... params) throws OccupiedPositionError {
            Helicopter helicopter = Game.getGame().getHelicopter();
            return new Building(new Vector2(helicopter.getPosition().x + ScreenInfo.width - 200f, random.nextFloat() * max + min));
        }
    };

    //Get a new instance of a certain type of object
    public abstract GameObject getObject(Random random, float max, float min, Object... params) throws OccupiedPositionError;
}
