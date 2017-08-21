package com.av.game;

import com.av.game.graphics.GameRenderer;
import com.av.game.graphics.ObjectAnimation;
import com.av.game.graphics.ObjectRender;
import com.av.game.gui.GameUI;
import com.av.game.input.InputHandler;
import com.av.game.input.InputObserver;
import com.av.game.logic.Game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HeliGame extends ApplicationAdapter {
	private static final String TAG = "HeliHandler";

	public static float VIEW_WIDTH;
	public static final float VIEW_HEIGHT = 780f;

	private SpriteBatch batch;
	private OrthographicCamera cam;

	private GameUI ui;

	private float stateTime = 0f;

	public HeliGame(InputHandler[] handlers) {
		super();
		InputObserver.createInstance();
		for(InputHandler handler : handlers)
			InputObserver.addInputListenner(handler);
	}
	
	@Override
	public void create () {
		VIEW_WIDTH = VIEW_HEIGHT * (Gdx.graphics.getWidth() / (float)Gdx.graphics.getHeight());

		Game.createInstance();
		GameRenderer.createInstance();
		Game.getGame().create();

        ui = new GameUI(Game.getGame());

		batch = new SpriteBatch();

		ObjectRender helicopter = new ObjectAnimation(Game.getGame().getHelicopter(), "Helicopter.png", 2, 4, 0.08f);
		helicopter.setRotation(-10f);
		GameRenderer.addRenderable(helicopter);

		cam = new OrthographicCamera(VIEW_WIDTH, VIEW_HEIGHT);
		cam.position.set(VIEW_WIDTH / 2f, VIEW_HEIGHT / 2f, 0);
		cam.update();
	}

	private void restart() {
		Game.getGame().create();
		GameRenderer.clear();
		ObjectRender helicopter = new ObjectAnimation(Game.getGame().getHelicopter(), "Helicopter.png", 2, 4, 0.08f);
		helicopter.setRotation(-10f);
		GameRenderer.addRenderable(helicopter);
	}

	@Override
	public void render () {
		cam.position.set(cam.viewportWidth / 2f + Game.getGame().getHelicopter().getPosition().x - 200f,
						cam.viewportHeight / 2f, 0);
		cam.update();
		batch.setProjectionMatrix(cam.combined);

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		InputObserver.getInstance().handleInput();

		Game.getGame().update(Gdx.graphics.getDeltaTime() );
		if(Game.getGame().isGameOver()) {
			restart();
			return;
		}

		stateTime += Gdx.graphics.getDeltaTime();

		batch.begin();
		GameRenderer.render(stateTime, batch);
        ui.render(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
