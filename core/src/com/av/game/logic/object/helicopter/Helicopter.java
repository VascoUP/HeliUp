package com.av.game.logic.object.helicopter;

import com.av.game.logic.object.PhysicsObject;
import com.av.game.logic.object.helicopter.component.base.BaseHeliDecorator;
import com.av.game.logic.object.helicopter.component.base.CoreHeliBase;
import com.av.game.logic.object.helicopter.component.base.HeliBase;
import com.av.game.logic.object.helicopter.component.fuel.CoreHeliFuel;
import com.av.game.logic.object.helicopter.component.fuel.HeliFuel;
import com.av.game.logic.object.helicopter.component.fuel.HeliFuelDecorator;
import com.av.game.logic.physics.CollisionObserver;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Helicopter extends PhysicsObject {
    private static String TAG = "Helicopter";

    private enum HeliState {UP, DOWN};
    private HeliState currState;

    private HeliBase heliBase;
    private HeliFuel heliFuel;

    public Helicopter(Vector2 position) {
        super(position);
        CollisionObserver.addCollisionObject(this);
        velocity_x = 10f;
        currState = HeliState.DOWN;
        heliBase = new CoreHeliBase();
        heliFuel = new CoreHeliFuel();
    }

    public HeliBase getHeliBase() {
        return heliBase;
    }

    public HeliFuel getHeliFuel() {
        return heliFuel;
    }

    public void setHeliBase(HeliBase heliBase) {
        this.heliBase = heliBase;
    }

    public void setHeliFuel(HeliFuel heliFuel) {
        this.heliFuel = heliFuel;
    }


    public void decorateBase(BaseHeliDecorator decorator) {
        heliBase = decorator;
    }

    public void decorateFuel(HeliFuelDecorator decorator) {
        heliFuel = decorator;
    }


    public void upForce() {
        if(currState == HeliState.UP)
            return;
        if(heliFuel.getFuel() > 0) {
            currState = HeliState.UP;
            setAcceleration_y(1f);
        }
    }

    public void resetForce() {
        if(currState == HeliState.DOWN)
            return;
        currState = HeliState.DOWN;
        setAcceleration_y(0);
    }


    public void update() {
        if(currState == HeliState.UP) {
            heliFuel.calcFuel();
            Gdx.app.log(TAG, heliFuel.getFuel() + "");
            if (heliFuel.getFuel() <= 0)
                resetForce();
        }
    }
}
