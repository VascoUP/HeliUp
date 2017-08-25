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
            Gdx.app.log(TAG, "is touched " + Game.getGame().getHelicopter().getPosition());
            if(Game.getGame().getHelicopter().getAcceleration_y() != 1f)
                Game.getGame().getHelicopter().upForce();
        } else if (Game.getGame().getHelicopter().getAcceleration_y() != 0f){
            Gdx.app.log(TAG, "not touching");
            Game.getGame().getHelicopter().resetForce();
        }
    }
}
