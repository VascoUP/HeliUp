package com.av.game;

import com.av.game.graphics.ObjectAnimation;
import com.av.game.input.HeliHandler;
import com.av.game.input.InputObserver;
import com.av.game.logic.Game;
import com.av.game.logic.physics.Physics;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HeliGame extends ApplicationAdapter {
	private static final String TAG = "HeliHandler";

	public static float VIEW_WIDTH;
	public static float VIEW_HEIGHT = 780f;

	private SpriteBatch batch;
	private OrthographicCamera cam;

	private float stateTime = 0f;

	private ObjectAnimation helicopter;
	
	@Override
	public void create () {
		VIEW_WIDTH = VIEW_HEIGHT * (Gdx.graphics.getWidth() / (float)Gdx.graphics.getHeight());

		Physics.createInstance();
		Game.createInstance();
		Game.getGame().create();
		InputObserver.createInstance();
		InputObserver.addInputListenner(new HeliHandler());

		batch = new SpriteBatch();

		helicopter = new ObjectAnimation(Game.getGame().getHelicopter(), "Helicopter.png", 2, 4, 0.08f);
		helicopter.setRotation(-10f);

		cam = new OrthographicCamera(VIEW_WIDTH, VIEW_HEIGHT);
		cam.position.set(VIEW_WIDTH / 2f, VIEW_HEIGHT / 2f, 0);
		cam.update();
	}

	private void restart() {
		Physics.getInstance().clear();
		Game.getGame().create();
		helicopter.setGameObject(Game.getGame().getHelicopter());
	}

	@Override
	public void render () {
		cam.position.set(cam.viewportWidth / 2f + Game.getGame().getHelicopter().getPosition().x - 100f,
						cam.viewportHeight / 2f, 0);
		cam.update();
		batch.setProjectionMatrix(cam.combined);

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stateTime += Gdx.graphics.getDeltaTime();

		InputObserver.getInstance().handleInput();

		Physics.getInstance().update();
		if(Game.getGame().endGame()) {
			restart();
			return;
		}

		batch.begin();
		helicopter.render(stateTime, batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		helicopter.dispose();
	}
}
