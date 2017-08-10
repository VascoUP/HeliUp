package com.av.game.logic.object.helicopter.component.fuel;

public interface HeliFuel {
    void calcFuel();
    int getCapacity();
    void setCapacity(int capacity);
    int getFuel();
    void setFuel(int fuel);
}
