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
            Gdx.app.log(TAG, "is touched " + Game.getGame().getHelicopter().getPosition());
            if(Game.getGame().getHelicopter().getAcceleration_y() != 1f)
                Game.getGame().getHelicopter().upForce();
        } else if (Game.getGame().getHelicopter().getAcceleration_y() != 0f){
            Gdx.app.log(TAG, "not touching");
            Game.getGame().getHelicopter().resetForce();
        }
    }
}
