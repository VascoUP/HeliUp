package com.av.game.input;

import com.av.game.HeliGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class MenuHandler implements InputHandler {
    private static final String TAG = "HeliHandler";

    private boolean touched = false;

    private HeliGame game;

    public MenuHandler(HeliGame game) {
        this.game = game;
    }

    @Override
    public void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            if(!touched)
                touched = true;
        } else if (touched){
            touched = false;
            game.toGame();
        }
    }
}
