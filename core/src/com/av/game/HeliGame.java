package com.av.game;

import com.av.game.graphics.GameRenderer;
import com.av.game.gui.GameUI;
import com.av.game.input.InputHandler;
import com.av.game.input.InputObserver;
import com.av.game.input.MenuHandler;
import com.av.game.logic.Game;
import com.av.game.logic.object.ObjectsNotifier;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class HeliGame extends ApplicationAdapter {
	private static final String TAG = "HeliHandler";

	private enum STATE {DEATHMENU, GAME};
	private STATE state = STATE.GAME;

	public static float VIEW_WIDTH;
	public static final float VIEW_HEIGHT = 780f;

	private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
	private OrthographicCamera cam;

	private GameUI ui;

	private float stateTime = 0f;

    private MenuHandler handler;

	public HeliGame(InputHandler[] handlers) {
		super();
		InputObserver.createInstance();
		for(InputHandler handler : handlers)
			InputObserver.addInputListenner(handler);
	}
	
	@Override
	public void create () {
		VIEW_WIDTH = VIEW_HEIGHT * (Gdx.graphics.getWidth() / (float)Gdx.graphics.getHeight());

        handler = new MenuHandler(this);

		Game.createInstance();
		Game.getGame().create();
		GameRenderer.createInstance();

        ui = new GameUI(Game.getGame());

		shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();

		GameRenderer.getInstance().objectCreated(Game.getGame().getHelicopter());

		cam = new OrthographicCamera(VIEW_WIDTH, VIEW_HEIGHT);
		cam.position.set(VIEW_WIDTH / 2f, VIEW_HEIGHT / 2f, 0);
		cam.update();
	}

	private void restart() {
		Game.getGame().create();
		GameRenderer.clear();
		GameRenderer.getInstance().objectCreated(Game.getGame().getHelicopter());
        ObjectsNotifier.addObserver(GameRenderer.getInstance());
	}

	public void toMenu() {
		state = STATE.DEATHMENU;
	}

	public void toGame() {
        state = STATE.GAME;
        restart();
    }

	@Override
	public void render () {
		cam.position.set(cam.viewportWidth / 2f + Game.getGame().getHelicopter().getPosition().x - 200f,
						cam.viewportHeight / 2f, 0);
		cam.update();
		batch.setProjectionMatrix(cam.combined);
        shapeRenderer.setProjectionMatrix(cam.combined);

		Gdx.gl.glClearColor(0.2f,0.6f,0.9f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glDisable(GL20.GL_BLEND);

		if(state == STATE.GAME) {
            InputObserver.getInstance().handleInput();
			Game.getGame().update(Gdx.graphics.getDeltaTime());
			if (Game.getGame().isGameOver()) {
                toMenu();
			}
		} else {
            handler.handleInput();
        }

		stateTime += Gdx.graphics.getDeltaTime();

		batch.begin();
		GameRenderer.render(stateTime, batch);
        ui.render(batch);
		batch.end();

        shapeRenderer.setColor(Color.PURPLE);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        GameRenderer.shapeRender(shapeRenderer);
        shapeRenderer.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
