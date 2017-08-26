package com.av.game.logic.object.helicopter.component.base;


public abstract class BaseHeliDecorator implements HeliBase {
    protected HeliBase heliBase;

    public BaseHeliDecorator(HeliBase heliBase) {
        this.heliBase = heliBase;
    }

    @Override
    public float getAccelerationY() {
        return heliBase.getAccelerationY();
    }

    @Override
    public float getVelocityX() {
        return heliBase.getVelocityX();
    }

    @Override
    public void setVelocityX(float velocity_x) {
        heliBase.setVelocityX(velocity_x);
    }
}
