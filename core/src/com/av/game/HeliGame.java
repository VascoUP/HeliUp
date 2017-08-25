package com.av.game;

import com.av.game.graphics.GameRenderer;
import com.av.game.input.InputHandler;
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

		GameRenderer.getInstance().objectCreated(Game.getGame().getHelicopter());
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
		if(state == STATE.GAME) {
            InputObserver.getInstance().handleInput();
			Game.getGame().update(Gdx.graphics.getDeltaTime());
			if (Game.getGame().isGameOver()) {
                toMenu();
			}
		} else {
            handler.handleInput();
        }

		GameRenderer.getInstance().render();
	}
	
	@Override
	public void dispose () {
		GameRenderer.dispose();
	}
}
