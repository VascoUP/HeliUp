package com.av.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class RocketGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture helicopterSheet;
	private Animation<TextureRegion> helicopterAnimation;
	private OrthographicCamera cam;

	private float stateTime;
	
	@Override
	public void create () {
		batch = new SpriteBatch();

		helicopterSheet = new Texture(Gdx.files.internal("Helicopter.png"));

		// Use the split utility method to create a 2D array of TextureRegions. This is
		// possible because this sprite sheet contains frames of equal size and they are
		// all aligned.
		TextureRegion[][] tmp = TextureRegion.split(helicopterSheet,
				helicopterSheet.getWidth() / 2,
				helicopterSheet.getHeight() / 4);

		TextureRegion[] helicopterFrames = new TextureRegion[2 * 4];
		int index = 0;
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 2; j++)
				helicopterFrames[index++] = tmp[i][j];

		helicopterAnimation = new Animation<TextureRegion>(0.08f, helicopterFrames);

		stateTime = 0f;

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		cam = new OrthographicCamera(800, 800 * (h / w));

		cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
		cam.update();

	}

	@Override
	public void render () {
		cam.update();
		batch.setProjectionMatrix(cam.combined);

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clear screen
		stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time

		// Get current frame of animation for the current stateTime
		TextureRegion currentFrame = helicopterAnimation.getKeyFrame(stateTime, true);
		batch.begin();
		batch.draw(currentFrame, 50, 50, currentFrame.getRegionWidth() / 2f, currentFrame.getRegionHeight() / 2f,
				currentFrame.getRegionWidth(), currentFrame.getRegionHeight(), 1f, 1f, -10f * stateTime); // Draw current frame at (50, 50)
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		helicopterSheet.dispose();
	}
}
