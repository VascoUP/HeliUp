package com.av.game.input;

import com.av.game.logic.Game;
import com.badlogic.gdx.Gdx;

public class HeliHandler implements InputHandler {
    private static final String TAG = "HeliHandler";

    public HeliHandler() {}

    @Override
    public void handleInput() {
        if (Gdx.input.isTouched()) {
            Gdx.app.log(TAG, "is touched " + Game.getGame().getHelicopter().getPosition());
            if(Game.getGame().getHelicopter().getAcceleration_y() != 1f)
                Game.getGame().getHelicopter().setAcceleration_y(1f);
        } else if (Game.getGame().getHelicopter().getAcceleration_y() != 0f){
            Gdx.app.log(TAG, "not touching");
            Game.getGame().getHelicopter().setAcceleration_y(0f);
        }
    }
}
