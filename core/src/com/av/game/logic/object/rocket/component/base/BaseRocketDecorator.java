package com.av.game.logic.object.rocket.component.base;


public abstract class BaseRocketDecorator implements RocketBase {
    protected RocketBase rocketBase;

    public BaseRocketDecorator(RocketBase rocketBase) {
        this.rocketBase = rocketBase;
    }

    @Override
    public void calcVel() {
        rocketBase.calcVel();
    }
}
