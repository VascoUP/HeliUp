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
    public int getCapacity() {
        return heliFuel.getCapacity();
    }

    @Override
    public void setCapacity(int capacity) {
        heliFuel.setCapacity(capacity);
    }

    @Override
    public int getFuel() {
        return heliFuel.getFuel();
    }

    @Override
    public void setFuel(int fuel) {
        heliFuel.setFuel(fuel);
    }
}
