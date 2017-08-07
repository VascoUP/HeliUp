package com.av.game.logic.object.rocket.component.fuel;

public abstract class RocketFuelDecorator implements RocketFuel {
    protected RocketFuel rocketFuel;

    public RocketFuelDecorator(RocketFuel rocketFuel) {
        this.rocketFuel = rocketFuel;
    }

    @Override
    public void calcFuel() {
        rocketFuel.calcFuel();
    }

    @Override
    public void calcFuelPerSec() {
        rocketFuel.calcFuelPerSec();
    }
}
