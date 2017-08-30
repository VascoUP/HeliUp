package com.av.game.screen.screen;

import com.av.game.graphics.GameRenderer;
import com.av.game.input.Input;
import com.av.game.input.InputObserver;

public class EndGameScreen extends AbstractScreen {

    @Override
    public void buildStage() {}

    @Override
    public void show() {
        //Clear previous screen input handlers
        InputObserver.clear();
        //And this Screen's input handler
        InputObserver.addInputListenner(Input.end_menu_handler);
    }

    @Override
    public void render(float delta) {
        GameRenderer.getInstance().render(0f);

        //Handle input
        InputObserver.getInstance().handleInput();
    }

    @Override
    public void dispose () {}
}
