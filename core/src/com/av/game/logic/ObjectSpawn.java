package com.av.game.logic;

import com.av.game.HeliGame;
import com.av.game.logic.object.building.Building;
import com.av.game.logic.object.helicopter.Helicopter;
import com.av.game.logic.object.item.Item;
import com.av.game.logic.object.item.Refuel;
import com.av.game.logic.throwable.OccupiedPositionError;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class ObjectSpawn {
    private static String TAG = "ObjectSpawn";

    private static final float FUEL_SPAWN_TIME = 2f;
    private static final float BUILDING_SPAWN_TIME = 2f;
    private static float FUEL_MAX_HEIGHT;
    private static float BUILDING_MAX_HEIGHT;
    private static float OBJECT_MIN_HEIGHT;
    private float fuelTimer = 1f;
    private float buildingTimer = 0f;

    private Game game;

    private Random random;

    public ObjectSpawn(Game game) {
        FUEL_MAX_HEIGHT = HeliGame.VIEW_HEIGHT - 200f;
        BUILDING_MAX_HEIGHT = HeliGame.VIEW_HEIGHT - 250f;
        OBJECT_MIN_HEIGHT = 75f;
        this.game = game;
        random = new Random();
    }

    public void update(float deltaTime) {
        updateBuilding(deltaTime);
        updateFuel(deltaTime);
    }

    private void updateBuilding(float deltaTime) {
        buildingTimer += deltaTime;
        Helicopter helicopter = game.getHelicopter();
        while(buildingTimer >= BUILDING_SPAWN_TIME) {
            buildingTimer -= BUILDING_SPAWN_TIME;
            Building building = null;
            float max_height = BUILDING_MAX_HEIGHT;
            try {
                building = new Building(new Vector2(helicopter.getPosition().x + HeliGame.VIEW_WIDTH, random.nextFloat() * max_height + OBJECT_MIN_HEIGHT - 780));
            } catch (OccupiedPositionError occupiedPositionError) {
                max_height = occupiedPositionError.getCollidingObject().getPosition().y;
                Gdx.app.log(TAG,"Added building colliding, new max height " + max_height);

                try {
                    building = new Building(new Vector2(helicopter.getPosition().x + HeliGame.VIEW_WIDTH, random.nextFloat() * max_height + OBJECT_MIN_HEIGHT - 780));
                } catch (OccupiedPositionError occupiedPositionError2) {
                    occupiedPositionError2.printStackTrace();
                    System.exit(0);
                }
            }

            game.addBuilding(building);
        }
    }

    private void updateFuel(float deltaTime) {
        fuelTimer += deltaTime;
        Helicopter helicopter = game.getHelicopter();
        while(fuelTimer >= FUEL_SPAWN_TIME) {
            fuelTimer -= FUEL_SPAWN_TIME;
            Item refuel = null;
            float min_height = OBJECT_MIN_HEIGHT;
            try {
                refuel = new Refuel(new Vector2(helicopter.getPosition().x + HeliGame.VIEW_WIDTH, random.nextFloat() * FUEL_MAX_HEIGHT + min_height));
            } catch (OccupiedPositionError occupiedPositionError) {
                min_height = occupiedPositionError.getCollidingObject().getCollision().getBoundingRectangle().getHeight() + occupiedPositionError.getCollidingObject().getPosition().y;
                Gdx.app.log(TAG,"Added item colliding, new min height " + min_height);

                try {
                    refuel = new Refuel(new Vector2(helicopter.getPosition().x + HeliGame.VIEW_WIDTH, random.nextFloat() * FUEL_MAX_HEIGHT + min_height));
                } catch (OccupiedPositionError occupiedPositionError2) {
                    occupiedPositionError2.printStackTrace();
                    System.exit(0);
                }
            }

            game.addItem(refuel);
        }
    }
}
