package com.av.game.logic;

import com.av.game.HeliGame;
import com.av.game.logic.object.GameObject;
import com.av.game.logic.object.building.Building;
import com.av.game.logic.object.helicopter.Helicopter;
import com.av.game.logic.object.item.IncreaseCapacity;
import com.av.game.logic.object.item.IncreaseVelocity;
import com.av.game.logic.object.item.Item;
import com.av.game.logic.object.item.Refuel;
import com.av.game.logic.throwable.OccupiedPositionError;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class ObjectSpawn {
    private static String TAG = "ObjectSpawn";

    private enum TYPE {REFUEL, RANDOM_ITEM, BUILDING}

    private static final float FUEL_SPAWN_DISTANCE = 1000f;
    private static final float ITEM_SPAWN_DISTANCE = 5100f;
    private static final float BUILDING_SPAWN_DISTANCE = 1000f;
    private static float FUEL_MAX_HEIGHT;
    private static float BUILDING_MAX_HEIGHT;
    private static float FUEL_MIN_HEIGHT;
    private static float BUILDING_MIN_HEIGHT;
    private float fuel_distance_spawner = 1000f;
    private float item_distance_spawner = 0f;
    private float building_distance_spawner = 500f;

    private Game game;

    private Random random;

    ObjectSpawn(Game game) {
        FUEL_MAX_HEIGHT = HeliGame.VIEW_HEIGHT - 290f;
        BUILDING_MAX_HEIGHT = HeliGame.VIEW_HEIGHT - 270f;
        FUEL_MIN_HEIGHT = 70f;
        BUILDING_MIN_HEIGHT = -660f;
        this.game = game;
        random = new Random();
    }

    void update(float delta_distance) {
        updateBuilding(delta_distance);
        updateFuel(delta_distance);
        updateItem(delta_distance);
    }

    private void updateBuilding(float deltaTime) {
        building_distance_spawner = spawn(TYPE.BUILDING, building_distance_spawner, deltaTime, BUILDING_SPAWN_DISTANCE, BUILDING_MAX_HEIGHT, BUILDING_MIN_HEIGHT);
    }

    private void updateFuel(float deltaTime) {
        fuel_distance_spawner = spawn(TYPE.REFUEL, fuel_distance_spawner, deltaTime, FUEL_SPAWN_DISTANCE, FUEL_MAX_HEIGHT, FUEL_MIN_HEIGHT);
    }

    private void updateItem(float deltaTime) {
        item_distance_spawner = spawn(TYPE.RANDOM_ITEM, item_distance_spawner, deltaTime, ITEM_SPAWN_DISTANCE, FUEL_MAX_HEIGHT, FUEL_MIN_HEIGHT);
    }

    private Item randomItem(float max_height, float min_height) throws OccupiedPositionError {
        Helicopter helicopter = game.getHelicopter();
        switch (random.nextInt(2)) {
            case 0:
                return new IncreaseVelocity(new Vector2(helicopter.getPosition().x + HeliGame.VIEW_WIDTH - 200f, random.nextFloat() * max_height + min_height));
            case 1:
                return new IncreaseCapacity(new Vector2(helicopter.getPosition().x + HeliGame.VIEW_WIDTH - 200f, random.nextFloat() * max_height + min_height));
        }
        return null;
    }

    private float spawn(TYPE type_spawn, float distance_since_spawn, float delta_distance, float spawn_distance, float max, float min) {
        distance_since_spawn += delta_distance;
        Helicopter helicopter = game.getHelicopter();

        while(distance_since_spawn >= spawn_distance) {
            GameObject object = null;
            float max_height = max;
            float min_height = min;

            try {
                if(type_spawn == TYPE.BUILDING)
                    object = new Building(new Vector2(helicopter.getPosition().x + HeliGame.VIEW_WIDTH - 200f, random.nextFloat() * max_height + min));
                else if(type_spawn == TYPE.REFUEL)
                    object = new Refuel(new Vector2(helicopter.getPosition().x + HeliGame.VIEW_WIDTH - 200f, random.nextFloat() * max + min_height));
                else
                    object = (GameObject)randomItem(max_height, min_height);
            } catch (OccupiedPositionError occupiedPositionError) {
                if(type_spawn == TYPE.BUILDING)
                    max_height = occupiedPositionError.getCollidingObject().getPosition().y;
                else
                    min_height = occupiedPositionError.getCollidingObject().getCollision().getBoundingRectangle().getHeight() + occupiedPositionError.getCollidingObject().getPosition().y;

                Gdx.app.log(TAG,"Added building colliding, new max height " + max_height + " and new min height " + min_height);

                if(min_height > max || max_height < min) {
                    distance_since_spawn -= 100f;
                    return distance_since_spawn;
                }

                try {
                    if(type_spawn == TYPE.BUILDING)
                        object = new Building(new Vector2(helicopter.getPosition().x + HeliGame.VIEW_WIDTH - 200f, random.nextFloat() * max_height + min));
                    else if(type_spawn == TYPE.REFUEL)
                        object = new Refuel(new Vector2(helicopter.getPosition().x + HeliGame.VIEW_WIDTH - 200f, random.nextFloat() * max + min_height));
                    else
                        object = (GameObject)randomItem(max_height, min_height);
                } catch (OccupiedPositionError occupiedPositionError2) {
                    occupiedPositionError2.printStackTrace();
                    System.exit(0);
                }
            }

            if(type_spawn == TYPE.BUILDING)
                game.addBuilding(object);
            else
                game.addItem((Item)object);

            distance_since_spawn -= spawn_distance;
        }

        return distance_since_spawn;
    }
}
