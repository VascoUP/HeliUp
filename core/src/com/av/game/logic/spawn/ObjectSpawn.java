package com.av.game.logic.spawn;

import com.av.game.logic.Game;
import com.av.game.logic.object.GameObject;
import com.av.game.logic.throwable.OccupiedPositionError;
import com.av.game.screen.util.ScreenInfo;

import java.util.Random;

public class ObjectSpawn {
    private static String TAG = "ObjectSpawn";

    //Singleton class: only one instance is allowed to exist
    private static ObjectSpawn instance;

    //Spawn distances
    private static final float FUEL_SPAWN_DISTANCE = 1000f;
    private static final float ITEM_SPAWN_DISTANCE = 5000f;
    private static final float BUILDING_SPAWN_DISTANCE = 1000f;

    //Spawn heights
    private static float FUEL_MAX_HEIGHT;
    private static float BUILDING_MAX_HEIGHT;
    private static float FUEL_MIN_HEIGHT;
    private static float BUILDING_MIN_HEIGHT;

    //Distance counters
    private float fuel_distance_spawner;
    private float item_distance_spawner;
    private float building_distance_spawner;

    private Random random;

    private ObjectSpawn() {
        //Define certain static values
        FUEL_MAX_HEIGHT = ScreenInfo.height - 290f;
        BUILDING_MAX_HEIGHT = ScreenInfo.height - 270f;
        FUEL_MIN_HEIGHT = 70f;
        BUILDING_MIN_HEIGHT = -660f;

        //Set counters to their initial value
        resetCounters();

        random = new Random();
    }

    public static void createInstance() {
        instance = new ObjectSpawn();
    }

    public static ObjectSpawn getInstance() {
        return instance;
    }

    public static void reset() {
        instance.resetCounters();
    }

    private void resetCounters() {
        fuel_distance_spawner = 1000f;
        item_distance_spawner = 250f;
        building_distance_spawner = 500f;
    }

    public void update(float delta_distance) {
        updateBuilding(delta_distance);
        updateFuel(delta_distance);
        updateItem(delta_distance);
    }

    private void updateBuilding(float deltaTime) {
        building_distance_spawner = spawn(ObjectEnum.BUILDING, building_distance_spawner, deltaTime, BUILDING_SPAWN_DISTANCE, BUILDING_MAX_HEIGHT, BUILDING_MIN_HEIGHT);
    }

    private void updateFuel(float deltaTime) {
        fuel_distance_spawner = spawn(ObjectEnum.REFUEL, fuel_distance_spawner, deltaTime, FUEL_SPAWN_DISTANCE, FUEL_MAX_HEIGHT, FUEL_MIN_HEIGHT);
    }

    private void updateItem(float deltaTime) {
        item_distance_spawner = spawn(ObjectEnum.RANDOM_ITEM, item_distance_spawner, deltaTime, ITEM_SPAWN_DISTANCE, FUEL_MAX_HEIGHT, FUEL_MIN_HEIGHT);
    }

    private float spawn(ObjectEnum type_spawn, float distance_since_spawn, float delta_distance, float spawn_distance, float max, float min) {
        //Update distance counter
        distance_since_spawn += delta_distance;

        //If distance counter is greater then spawn distance, spawn the object
        while(distance_since_spawn >= spawn_distance)
            distance_since_spawn = spawnObject(type_spawn, distance_since_spawn, spawn_distance, max, min);

        return distance_since_spawn;
    }

    private float spawnObject(ObjectEnum type_spawn, float distance_since_spawn, float spawn_distance, float max, float min) {
        GameObject object;

        try {
            //Try to spawn object on a random position
            object = type_spawn.getObject(random, max, min);
        } catch (OccupiedPositionError occupiedPositionError) {
            //On failure...
            float max_height = max;
            float min_height = min;

            //... set max_height or min_height to new values that will work
            if(type_spawn == ObjectEnum.BUILDING)
                max_height = occupiedPositionError.getCollidingObject().getPosition().y;
            else
                min_height = occupiedPositionError.getCollidingObject().getCollision().getBoundingRectangle().getHeight() + occupiedPositionError.getCollidingObject().getPosition().y;

            //If any of the values is invalid delay the spawn
            if(min_height > max || max_height < min)
                return distance_since_spawn - 100f;

            //Retry spawning
            try {
                object = type_spawn.getObject(random, max_height, min_height);
            } catch (OccupiedPositionError occupiedPositionError2) {
                //On failure, delay spawn
                occupiedPositionError2.printStackTrace();
                return distance_since_spawn - 100f;
            }
        }

        Game.getGame().addObject(object);
        return distance_since_spawn - spawn_distance;
    }
}
