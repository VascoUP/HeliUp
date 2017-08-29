package com.av.game.graphics;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public interface Renderable {
    void render(float state_time, SpriteBatch batch);
    void shapeRender(ShapeRenderer shape_renderer);
}
