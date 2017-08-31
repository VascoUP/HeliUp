package com.av.game.input;

public class MenuHandler implements InputHandler {
    private static final String TAG = "HeliHandler";

    private boolean realeased = false;
    private boolean touched = false;

    public MenuHandler() {}

    @Override
    public void handleInput() {
        /*if (Gdx.input.isKeyPressed(Input.Keys.SPACE) || Gdx.input.isTouched()) {
            if(realeased && !touched)
                touched = true;
        } else if (!realeased) {
            realeased = true;
        } else if (touched) {
            touched = false;
            ScreenManager.getInstance().showScreen(ScreenEnum.GAME);
        }*/
    }
}
