package com.av.game.graphics;

import com.av.game.HeliGame;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class VisualObject implements Renderable {
    private Sprite sprite;

    private Vector2 position;
    private Vector2 velocity;

    private boolean is_z0;
    private float state_time = -1f;

    public VisualObject(Vector2 position, Vector2 velocity, Sprite sprite, boolean is_z0) {
        this.position = position;
        this.velocity = velocity;
        this.sprite = sprite;
        this.is_z0 = is_z0;
    }

    @Override
    public void render(float state_time, SpriteBatch batch) {
        if(this.state_time == -1f)
            this.state_time = state_time;
        float delta_time = state_time - this.state_time;
        position.x += velocity.x * delta_time;
        position.y += velocity.y * delta_time;
        batch.draw(sprite, position.x, position.y,
                sprite.getRegionWidth() / 2f, sprite.getRegionHeight() / 2f,
                sprite.getRegionWidth(), sprite.getRegionHeight(),
                1, 1, 0);
        Vector3 cam_position = GameRenderer.getCamPosition();
        if(position.x + sprite.getWidth() < cam_position.x - HeliGame.VIEW_WIDTH / 2f) {
            if (is_z0)
                GameRenderer.getInstance().rmZ0(this);
            else
                GameRenderer.getInstance().rmZ2(this);
        }
    }

    @Override
    public void shapeRender(ShapeRenderer shape_renderer) {}
}
