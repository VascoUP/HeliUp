package com.av.game.graphics;

import com.av.game.HeliGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class VisualObject implements Renderable {
    private Sprite sprite;

    private Vector2 position;
    private Vector2 velocity;

    private boolean isZ0;

    public VisualObject(Vector2 position, Vector2 velocity, Sprite sprite, boolean isZ0) {
        this.position = position;
        this.velocity = velocity;
        this.sprite = sprite;
        this.isZ0 = isZ0;
    }

    @Override
    public void render(float stateTime, SpriteBatch batch) {
        position.x += velocity.x * Gdx.graphics.getDeltaTime();
        position.y += velocity.y * Gdx.graphics.getDeltaTime();
        batch.draw(sprite, position.x, position.y,
                sprite.getRegionWidth() / 2f, sprite.getRegionHeight() / 2f,
                sprite.getRegionWidth(), sprite.getRegionHeight(),
                1, 1, 0);
        Vector3 cam_position = GameRenderer.getCamPosition();
        if(position.x + sprite.getWidth() < cam_position.x - HeliGame.VIEW_WIDTH / 2f) {
            if (isZ0)
                GameRenderer.getInstance().rmZ0(this);
            else
                GameRenderer.getInstance().rmZ2(this);
        }

    }

    @Override
    public void shapeRender(ShapeRenderer shapeRenderer) {}
}
