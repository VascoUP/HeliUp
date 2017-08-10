package com.av.game.graphics;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Renderable {
    void render(float stateTime, SpriteBatch batch );
    void dispose();
}
