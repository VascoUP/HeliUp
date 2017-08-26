package com.av.game.logic.object.helicopter.component.base;

public class IncreaseHeliVelocity extends BaseHeliDecorator {
    private final float velocity_x = 1f;

    public IncreaseHeliVelocity(HeliBase heliBase) {
        super(heliBase);
    }

    @Override
    public float getVelocityX() {
        return heliBase.getVelocityX() + velocity_x;
    }
}
