package com.av.game.logic.object.helicopter;

import com.av.game.logic.object.PhysicsObject;
import com.av.game.logic.object.helicopter.component.base.BaseHeliDecorator;
import com.av.game.logic.object.helicopter.component.base.CoreHeliBase;
import com.av.game.logic.object.helicopter.component.base.HeliBase;
import com.av.game.logic.object.helicopter.component.fuel.CoreHeliFuel;
import com.av.game.logic.object.helicopter.component.fuel.HeliFuel;
import com.av.game.logic.object.helicopter.component.fuel.HeliFuelDecorator;
import com.av.game.logic.physics.CollisionObserver;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

public class Helicopter extends PhysicsObject {
    private static String TAG = "Helicopter";

    private enum HeliState {UP, DOWN}

    private HeliState currState;

    private HeliBase heliBase;
    private HeliFuel heliFuel;

    private static final float max_rotation = 0f;
    private static final float min_rotation = -30f;

    public Helicopter(Vector2 position) {
        super(position, -10, new Polygon(new float[] {1,37,4,6,51,0,78,0,93,15,104,37,104,39,12,49}));
        CollisionObserver.addCollisionObject(this);
        currState = HeliState.DOWN;
        heliBase = new CoreHeliBase();
        heliFuel = new CoreHeliFuel();
        velocity_x = heliBase.getVelocityX();
    }

    public HeliBase getHeliBase() {
        return heliBase;
    }

    public HeliFuel getHeliFuel() {
        return heliFuel;
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
            setAccelerationY(heliBase.getAccelerationY());
        }
    }

    public void resetForce() {
        if(currState == HeliState.DOWN)
            return;
        currState = HeliState.DOWN;
        setAccelerationY(0);
    }


    public void update() {
        if(currState == HeliState.UP) {
            heliFuel.calcFuel();
            this.setRotation(this.getRotation() + (max_rotation - this.getRotation()) / 20f);
            if (heliFuel.getFuel() <= 0)
                resetForce();
        } else {
            this.setRotation(this.getRotation() - (this.getRotation() - min_rotation) / 20f);
        }
    }

    @Override
    public float getVelocityX() {
        return heliBase.getVelocityX();
    }
}
