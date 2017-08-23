package com.av.game.graphics;

import com.av.game.logic.object.GameObject;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ObjectSprite extends ObjectRender {
    private Sprite sprite;

    public ObjectSprite(GameObject gameObject, Texture texture) {
        super(gameObject);
        sprite = new Sprite(texture);
    }

    @Override
    public void render(float stateTime, SpriteBatch batch) {
        batch.draw(sprite, gameObject.getPosition().x, gameObject.getPosition().y,
                sprite.getRegionWidth() / 2f, sprite.getRegionHeight() / 2f,
                sprite.getRegionWidth(), sprite.getRegionHeight(),
                scale_x, scale_y,
                gameObject.getRotation());
    }
}
