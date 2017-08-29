package com.av.game.graphics;

import com.av.game.logic.object.GameObject;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ObjectSprite extends ObjectRender {
    private Sprite sprite;

    public ObjectSprite(GameObject game_object, Texture texture) {
        super(game_object);
        sprite = new Sprite(texture);
    }

    @Override
    public void render(float state_time, SpriteBatch batch) {
        batch.draw(sprite, game_object.getPosition().x, game_object.getPosition().y,
                sprite.getRegionWidth() / 2f, sprite.getRegionHeight() / 2f,
                sprite.getRegionWidth(), sprite.getRegionHeight(),
                scale_x, scale_y,
                game_object.getRotation());
    }
}
