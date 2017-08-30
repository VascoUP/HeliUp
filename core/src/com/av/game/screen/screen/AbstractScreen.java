package com.av.game.screen.screen;

import com.av.game.screen.util.ScreenInfo;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

public abstract class AbstractScreen implements Screen {
    protected AbstractScreen() {}

    // Subclasses must load actors in this method
    public abstract void buildStage();

    @Override
    public void render(float delta) {
        // Clear screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void show() {}

    @Override
    public void resize(int width, int height) {
        //On screen resize change width
        ScreenInfo.width = ScreenInfo.height * (Gdx.graphics.getWidth() / (float)Gdx.graphics.getHeight());
    }

    @Override public void hide() {}
    @Override public void pause() {}
    @Override public void resume() {}
}
