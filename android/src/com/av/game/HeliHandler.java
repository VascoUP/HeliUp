package com.av.game;

import com.av.game.input.InputHandler;
import com.av.game.logic.Game;
import com.badlogic.gdx.Gdx;

public class HeliHandler implements InputHandler {
    private static final String TAG = "HeliHandler";

    public HeliHandler() {}

    @Override
    public void handleInput() {
        if (Gdx.input.isTouched()) {
            Gdx.app.log(TAG, "is touched " + Game.getGame().getHelicopter().getPosition());
            if(Game.getGame().getHelicopter().getAccelerationY() != 1f)
                Game.getGame().getHelicopter().upForce();
        } else if (Game.getGame().getHelicopter().getAccelerationY() != 0f){
            Gdx.app.log(TAG, "not touching");
            Game.getGame().getHelicopter().resetForce();
        }
    }
}
