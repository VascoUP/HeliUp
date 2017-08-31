package com.av.game;

import com.av.game.input.InputHandler;
import com.av.game.logic.Game;
import com.badlogic.gdx.Gdx;

class HeliHandler implements InputHandler {
    private static final String TAG = "HeliHandler";

    HeliHandler() {}

    @Override
    public void handleInput() {
        if (Gdx.input.isTouched()) {
            if(Game.getGame().getHelicopter().getAccelerationY() != 1f)
                Game.getGame().getHelicopter().upForce();
        } else if (Game.getGame().getHelicopter().getAccelerationY() != 0f){
            Game.getGame().getHelicopter().resetForce();
        }
    }
}
