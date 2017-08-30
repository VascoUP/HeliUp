package com.av.game.input;

import com.av.game.screen.util.ScreenEnum;
import com.av.game.screen.util.ScreenManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class MenuHandler implements InputHandler {
    private static final String TAG = "HeliHandler";

    private boolean realeased = false;
    private boolean touched = false;

    public MenuHandler() {}

    @Override
    public void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) || Gdx.input.isTouched()) {
            if(realeased && !touched)
                touched = true;
        } else if (!realeased) {
            realeased = true;
        } else if (touched) {
            touched = false;
            ScreenManager.getInstance().showScreen(ScreenEnum.GAME);
        }
    }
}
