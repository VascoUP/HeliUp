package com.av.game.screen.screen;

import com.av.game.input.Input;
import com.av.game.input.InputObserver;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

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
        //Clear screen
        Gdx.gl.glClearColor(0.6f,0.6f,0.9f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glDisable(GL20.GL_BLEND);

        //Handle input
        InputObserver.getInstance().handleInput();
    }

    @Override
    public void dispose () {}
}
