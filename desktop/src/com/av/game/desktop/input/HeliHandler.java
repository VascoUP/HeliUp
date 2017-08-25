package com.av.game.desktop.input;

import com.av.game.input.InputHandler;
import com.av.game.logic.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class HeliHandler implements InputHandler {
    private static final String TAG = "HeliHandler";

    public HeliHandler() {}

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
