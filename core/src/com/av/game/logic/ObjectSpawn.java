package com.av.game.logic;

import com.av.game.HeliGame;
import com.av.game.logic.object.GameObject;
import com.av.game.logic.object.building.Building;
import com.av.game.logic.object.helicopter.Helicopter;
import com.av.game.logic.object.item.IncreaseCapacity;
import com.av.game.logic.object.item.Item;
import com.av.game.logic.object.item.Refuel;
import com.av.game.logic.throwable.OccupiedPositionError;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class ObjectSpawn {
    private static String TAG = "ObjectSpawn";

    private enum TYPE {REFUEL, RANDOM_ITEM, BUILDING};

    private static final float FUEL_SPAWN_TIME = 3f;
    private static final float ITEM_SPAWN_TIME = 15.5f;
    private static final float BUILDING_SPAWN_TIME = 3f;
    private static float FUEL_MAX_HEIGHT;
    private static float BUILDING_MAX_HEIGHT;
    private static float FUEL_MIN_HEIGHT;
    private static float BUILDING_MIN_HEIGHT;
    private float fuelTimer = 3f;
    private float itemTimer = 0f;
    private float buildingTimer = 1.5f;

    private Game game;

    private Random random;

    public ObjectSpawn(Game game) {
        FUEL_MAX_HEIGHT = HeliGame.VIEW_HEIGHT - 270f;
        BUILDING_MAX_HEIGHT = HeliGame.VIEW_HEIGHT - 220f;
        FUEL_MIN_HEIGHT = 70f;
        BUILDING_MIN_HEIGHT = -660f;
        this.game = game;
        random = new Random();
    }

    public void update(float deltaTime) {
        updateBuilding(deltaTime);
        updateFuel(deltaTime);
        updateItem(deltaTime);
    }

    private Item randomItem(float max_height, float min_height) throws OccupiedPositionError {
        Helicopter helicopter = game.getHelicopter();
        return new IncreaseCapacity(new Vector2(helicopter.getPosition().x + HeliGame.VIEW_WIDTH - 200f, random.nextFloat() * max_height + min_height));
    }

    private void updateBuilding(float deltaTime) {
        /*buildingTimer += deltaTime;
        Helicopter helicopter = game.getHelicopter();
        while(buildingTimer >= BUILDING_SPAWN_TIME) {
            buildingTimer -= BUILDING_SPAWN_TIME;
            Building building = null;
            float max_height = BUILDING_MAX_HEIGHT;
            try {
                building = new Building(new Vector2(helicopter.getPosition().x + HeliGame.VIEW_WIDTH - 200f, random.nextFloat() * max_height + BUILDING_MIN_HEIGHT - 780f));
            } catch (OccupiedPositionError occupiedPositionError) {
                max_height = occupiedPositionError.getCollidingObject().getPosition().y;
                Gdx.app.log(TAG,"Added building colliding, new max height " + max_height);

                try {
                    building = new Building(new Vector2(helicopter.getPosition().x + HeliGame.VIEW_WIDTH - 200f, random.nextFloat() * max_height + BUILDING_MIN_HEIGHT - 780f));
                } catch (OccupiedPositionError occupiedPositionError2) {
                    occupiedPositionError2.printStackTrace();
                    System.exit(0);
                }
            }

            game.addBuilding(building);
        }*/
        buildingTimer = spawn(TYPE.BUILDING, buildingTimer, deltaTime, BUILDING_SPAWN_TIME, BUILDING_MAX_HEIGHT, BUILDING_MIN_HEIGHT);
    }

    private void updateFuel(float deltaTime) {
        /*fuelTimer += deltaTime;
        Helicopter helicopter = game.getHelicopter();
        while(fuelTimer >= FUEL_SPAWN_TIME) {
            fuelTimer -= FUEL_SPAWN_TIME;
            Item refuel = null;
            float min_height = FUEL_MIN_HEIGHT;
            try {
                refuel = new Refuel(new Vector2(helicopter.getPosition().x + HeliGame.VIEW_WIDTH - 200f, random.nextFloat() * FUEL_MAX_HEIGHT + min_height));
            } catch (OccupiedPositionError occupiedPositionError) {
                min_height = occupiedPositionError.getCollidingObject().getCollision().getBoundingRectangle().getHeight() + occupiedPositionError.getCollidingObject().getPosition().y;
                Gdx.app.log(TAG,"Added refuel colliding, new min height " + min_height);

                if(min_height > FUEL_MAX_HEIGHT) {
                    fuelTimer -= 1f;
                    break;
                }

                try {
                    refuel = new Refuel(new Vector2(helicopter.getPosition().x + HeliGame.VIEW_WIDTH - 200f, random.nextFloat() * FUEL_MAX_HEIGHT + min_height));
                } catch (OccupiedPositionError occupiedPositionError2) {
                    occupiedPositionError2.printStackTrace();
                    System.exit(0);
                }
            }

            game.addItem(refuel);
        }*/

        fuelTimer = spawn(TYPE.REFUEL, fuelTimer, deltaTime, FUEL_SPAWN_TIME, FUEL_MAX_HEIGHT, FUEL_MIN_HEIGHT);
    }

    private void updateItem(float deltaTime) {
        /*itemTimer += deltaTime;
        Helicopter helicopter = game.getHelicopter();
        while(itemTimer >= ITEM_SPAWN_TIME) {
            Item refuel = null;
            float min_height = FUEL_MIN_HEIGHT;
            try {
                refuel = new Refuel(new Vector2(helicopter.getPosition().x + HeliGame.VIEW_WIDTH - 200f, random.nextFloat() * FUEL_MAX_HEIGHT + min_height));
            } catch (OccupiedPositionError occupiedPositionError) {
                min_height = occupiedPositionError.getCollidingObject().getCollision().getBoundingRectangle().getHeight() + occupiedPositionError.getCollidingObject().getPosition().y;
                Gdx.app.log(TAG,"Added item colliding, new min height " + min_height);

                if(min_height > FUEL_MAX_HEIGHT) {
                    itemTimer -= 1f;
                    break;
                }

                try {
                    refuel = new Refuel(new Vector2(helicopter.getPosition().x + HeliGame.VIEW_WIDTH - 200f, random.nextFloat() * FUEL_MAX_HEIGHT + min_height));
                } catch (OccupiedPositionError occupiedPositionError2) {
                    occupiedPositionError2.printStackTrace();
                    System.exit(0);
                }
            }

            game.addItem(refuel);
            itemTimer -= ITEM_SPAWN_TIME;
        }*/

        itemTimer = spawn(TYPE.RANDOM_ITEM, itemTimer, deltaTime, ITEM_SPAWN_TIME, FUEL_MAX_HEIGHT, FUEL_MIN_HEIGHT);
    }

    private float spawn(TYPE type_spawn, float spawn_timer, float delta_time, float spawn_time, float max, float min) {
        spawn_timer += delta_time;
        Helicopter helicopter = game.getHelicopter();

        while(spawn_timer >= spawn_time) {
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
                    spawn_timer -= 1f;
                    return spawn_timer;
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

            spawn_timer -= spawn_time;
        }

        return spawn_timer;
    }
}
