package com.av.game;

import com.av.game.assets.AssetManager;
import com.av.game.graphics.GameRenderer;
import com.av.game.input.InputObserver;
import com.av.game.logic.Game;
import com.av.game.screen.util.ScreenEnum;
import com.av.game.screen.util.ScreenManager;

public class HeliGame extends com.badlogic.gdx.Game {
	@Override
	public void create () {
        AssetManager.createInstance();

		//Initialize input observer
		InputObserver.createInstance();

		//Start game by initializing Screen
        ScreenManager.getInstance().initialize(this);

        //Create a new instance of Game
        Game.createInstance();
        Game.getGame().create();

        //Create new instance of GameRenderer
        GameRenderer.createInstance();
        GameRenderer.getInstance().objectCreated(Game.getGame().getHelicopter());

        ScreenManager.getInstance().showScreen(ScreenEnum.COUNT_DOWN);
	}

    @Override
    public void dispose() {
        super.dispose();
        GameRenderer.dispose();
        AssetManager.getInstance().dispose();
    }
}
