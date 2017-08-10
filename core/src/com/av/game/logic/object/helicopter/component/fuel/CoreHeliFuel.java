package com.av.game.logic.object.helicopter.component.fuel;

public class CoreHeliFuel implements HeliFuel {
    private int capacity;
    private int fuelPerFrame;
    private int currFuel;

    public CoreHeliFuel() {
        capacity = 200;
        currFuel = 200;
        fuelPerFrame = 1;
    }

    @Override
    public void calcFuel() {
        currFuel -= fuelPerFrame;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public int getFuel() {
        return currFuel;
    }

    @Override
    public void setFuel(int fuel) {
        currFuel = fuel;
    }
}
