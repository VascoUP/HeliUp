package com.av.game.logic;

import com.av.game.HeliGame;
import com.av.game.logic.object.helicopter.Helicopter;
import com.av.game.logic.object.item.Refuel;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class ObjectSpawn {
    private static String TAG = "ObjectSpawn";

    /*private enum FuelState {FUEL_ADDED, FUEL_TO_ADD};
    private FuelState state = FuelState.FUEL_TO_ADD;*/

    private static final float FUEL_SPAWN_TIME = 2f;
    private static float OBJECT_MAX_HEIGHT;
    private static float OBJECT_MIN_HEIGHT;
    private float fuelTimer = 0f;

    private Game game;

    private Random random;

    public ObjectSpawn(Game game) {
        OBJECT_MAX_HEIGHT = HeliGame.VIEW_HEIGHT - 100f;
        OBJECT_MIN_HEIGHT = 50f;
        this.game = game;
        random = new Random();
    }

    public void update(float deltaTime) {
        updateFuel(deltaTime);
    }

    public void updateFuel(float deltaTime) {
        fuelTimer += deltaTime;
        Gdx.app.log(TAG, "Fuel timer " + fuelTimer);
        Helicopter helicopter = game.getHelicopter();
        while(fuelTimer > FUEL_SPAWN_TIME) {
            fuelTimer -= FUEL_SPAWN_TIME;
            game.addItem(new Refuel(new Vector2(helicopter.getPosition().x + HeliGame.VIEW_WIDTH, random.nextFloat() * OBJECT_MAX_HEIGHT + OBJECT_MIN_HEIGHT)));
        }
    }
}
