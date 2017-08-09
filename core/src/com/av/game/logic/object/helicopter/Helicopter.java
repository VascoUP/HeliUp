package com.av.game.logic.object.helicopter;

import com.av.game.logic.object.PhysicsObject;
import com.av.game.logic.object.helicopter.component.base.BaseHeliDecorator;
import com.av.game.logic.object.helicopter.component.base.CoreHeliBase;
import com.av.game.logic.object.helicopter.component.base.HeliBase;
import com.av.game.logic.object.helicopter.component.fuel.CoreHeliFuel;
import com.av.game.logic.object.helicopter.component.fuel.HeliFuel;
import com.av.game.logic.object.helicopter.component.fuel.HeliFuelDecorator;
import com.badlogic.gdx.math.Vector2;

public class Helicopter extends PhysicsObject {
    private HeliBase heliBase;
    private HeliFuel heliFuel;

    public Helicopter(Vector2 position) {
        super(position);
        velocity_x = 10f;
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
}
