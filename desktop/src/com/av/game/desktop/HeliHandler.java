package com.av.game.desktop;

import com.av.game.input.InputHandler;
import com.av.game.logic.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

class HeliHandler implements InputHandler {
    private static final String TAG = "HeliHandler";

    HeliHandler() {}

    @Override
    public void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            if(Game.getGame().getHelicopter().getAccelerationY() != 1f)
                Game.getGame().getHelicopter().upForce();
        } else if (Game.getGame().getHelicopter().getAccelerationY() != 0f) {
            Game.getGame().getHelicopter().resetForce();
        }
    }
}
