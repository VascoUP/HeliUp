package com.av.game;

import com.av.game.graphics.GameRenderer;
import com.av.game.input.Input;
import com.av.game.input.InputObserver;
import com.av.game.input.MenuHandler;
import com.av.game.logic.Game;
import com.av.game.logic.object.ObjectsNotifier;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;

public class HeliGame extends ApplicationAdapter {
	private static final String TAG = "HeliHandler";

	private enum STATE {DEATHMENU, GAME}

    private STATE state = STATE.GAME;

	public static float VIEW_WIDTH;
	public static final float VIEW_HEIGHT = 780f;

	public HeliGame() {
		super();
		InputObserver.createInstance();
		InputObserver.addInputListenner(Input.game_handler);
	}
	
	@Override
	public void create () {
		VIEW_WIDTH = VIEW_HEIGHT * (Gdx.graphics.getWidth() / (float)Gdx.graphics.getHeight());

		Input.end_menu_handler = new MenuHandler(this);

		Game.createInstance();
		Game.getGame().create();
		GameRenderer.createInstance();

		GameRenderer.getInstance().objectCreated(Game.getGame().getHelicopter());
	}

	private void restart() {
		Game.getGame().create();
		GameRenderer.clear();
		GameRenderer.getInstance().objectCreated(Game.getGame().getHelicopter());
        ObjectsNotifier.addObserver(GameRenderer.getInstance());
	}

	public void toMenu() {
		InputObserver.clear();
		InputObserver.addInputListenner(Input.end_menu_handler);
		state = STATE.DEATHMENU;
	}

	public void toGame() {
		InputObserver.clear();
		InputObserver.addInputListenner(Input.game_handler);
        state = STATE.GAME;
        restart();
    }

	@Override
	public void render () {
		InputObserver.getInstance().handleInput();
		if(state == STATE.GAME) {
			Game.getGame().update(Gdx.graphics.getDeltaTime());
			if (Game.getGame().isGameOver()) {
                toMenu();
			}
			GameRenderer.getInstance().render(Gdx.graphics.getDeltaTime());
		} else {
			GameRenderer.getInstance().render(0f);
        }
	}
	
	@Override
	public void dispose () {
		GameRenderer.dispose();
	}
}
