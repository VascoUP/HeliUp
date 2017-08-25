package com.av.game.logic.object.helicopter.component.base;


public abstract class BaseHeliDecorator implements HeliBase {
    protected HeliBase heliBase;

    public BaseHeliDecorator(HeliBase heliBase) {
        this.heliBase = heliBase;
    }
}
