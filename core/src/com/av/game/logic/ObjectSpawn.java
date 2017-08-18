package com.av.game.logic;

import com.av.game.HeliGame;
import com.av.game.logic.object.helicopter.Helicopter;
import com.av.game.logic.object.item.Refuel;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class ObjectSpawn {
    private static String TAG = "ObjectSpawn";

    private enum FuelState {FUEL_ADDED, FUEL_TO_ADD};
    private FuelState state = FuelState.FUEL_TO_ADD;

    private static final float FUEL_SPAWN_TIME = 1f;
    private float fuelTimer = 0f;

    private Game game;

    private Random random;

    public ObjectSpawn(Game game) {
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
        /*while(fuelTimer > 1f) {
            fuelTimer -= 1f;
            game.addItem(new Refuel(new Vector2(helicopter.getPosition().x + HeliGame.VIEW_WIDTH, random.nextFloat() * (HeliGame.VIEW_HEIGHT - 100.0f) + 50.0f)));
        }*/
        if(state == FuelState.FUEL_TO_ADD && helicopter.getHeliFuel().getFuel() < 100) {
            Gdx.app.log(TAG, "Spawn item");
            game.addItem(new Refuel(new Vector2(helicopter.getPosition().x + HeliGame.VIEW_WIDTH, random.nextFloat() * (HeliGame.VIEW_HEIGHT - 100.0f) + 50.0f)));
            state = FuelState.FUEL_ADDED;
        } else if(state == FuelState.FUEL_ADDED && helicopter.getHeliFuel().getFuel() >= 100)
            state = FuelState.FUEL_TO_ADD;
    }
}
