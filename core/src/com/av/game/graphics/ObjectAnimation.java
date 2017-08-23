package com.av.game.graphics;

import com.av.game.logic.object.GameObject;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ObjectAnimation extends ObjectRender {
    private Animation<TextureRegion> animation;

    public ObjectAnimation(GameObject gameObject, Texture sheet, int n_cols, int n_lins, float fps) {
        super(gameObject);

        TextureRegion[][] tmp = TextureRegion.split(sheet,
                sheet.getWidth() / n_cols,
                sheet.getHeight() / n_lins);

        TextureRegion[] helicopterFrames = new TextureRegion[n_cols * n_lins];
        int index = 0;
        for (int i = 0; i < n_lins; i++)
            for (int j = 0; j < n_cols; j++)
                helicopterFrames[index++] = tmp[i][j];

        animation = new Animation<TextureRegion>(fps, helicopterFrames);
    }

    @Override
    public void render(float stateTime, SpriteBatch batch) {
        TextureRegion currentFrame = animation.getKeyFrame(stateTime, true);

        batch.draw(currentFrame, gameObject.getPosition().x, gameObject.getPosition().y,
                currentFrame.getRegionWidth() / 2f, currentFrame.getRegionHeight() / 2f,
                currentFrame.getRegionWidth(), currentFrame.getRegionHeight(),
                scale_x, scale_y,
                gameObject.getRotation());
    }
}
