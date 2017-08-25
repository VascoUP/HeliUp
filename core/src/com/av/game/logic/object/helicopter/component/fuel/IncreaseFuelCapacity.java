package com.av.game.logic.object.helicopter.component.fuel;

public class IncreaseFuelCapacity extends HeliFuelDecorator {
    private static final int fuelCapacity = 40;

    public IncreaseFuelCapacity(HeliFuel heliFuel) {
        super(heliFuel);
    }

    @Override
    public int getCapacity() {
        return heliFuel.getCapacity() + fuelCapacity;
    }
}
