package com.av.game.logic.object.rocket;

import com.av.game.logic.object.GameObject;
import com.av.game.logic.object.rocket.component.base.BaseRocketDecorator;
import com.av.game.logic.object.rocket.component.base.CoreRocketBase;
import com.av.game.logic.object.rocket.component.base.RocketBase;
import com.av.game.logic.object.rocket.component.fuel.CoreRocketFuel;
import com.av.game.logic.object.rocket.component.fuel.RocketFuel;
import com.av.game.logic.object.rocket.component.fuel.RocketFuelDecorator;

public class Rocket extends GameObject{
    private RocketBase rocketBase;
    private RocketFuel rocketFuel;

    public Rocket() {
        rocketBase = new CoreRocketBase();
        rocketFuel = new CoreRocketFuel();
    }

    public RocketBase getRocketBase() {
        return rocketBase;
    }

    public RocketFuel getRocketFuel() {
        return rocketFuel;
    }

    public void setRocketBase(RocketBase rocketBase) {
        this.rocketBase = rocketBase;
    }

    public void setRocketFuel(RocketFuel rocketFuel) {
        this.rocketFuel = rocketFuel;
    }


    public void decorateBase(BaseRocketDecorator decorator) {
        rocketBase = decorator;
    }

    public void decorateFuel(RocketFuelDecorator decorator) {
        rocketFuel = decorator;
    }
}
