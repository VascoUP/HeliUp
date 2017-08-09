package com.av.game.logic.object.helicopter.component.fuel;

public abstract class HeliFuelDecorator implements HeliFuel {
    protected HeliFuel heliFuel;

    public HeliFuelDecorator(HeliFuel heliFuel) {
        this.heliFuel = heliFuel;
    }

    @Override
    public void calcFuel() {
        heliFuel.calcFuel();
    }

    @Override
    public void calcFuelPerSec() {
        heliFuel.calcFuelPerSec();
    }
}
