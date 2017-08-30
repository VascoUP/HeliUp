package com.av.game;

import com.av.game.graphics.GameRenderer;
import com.av.game.input.Input;
import com.av.game.input.InputObserver;
import com.av.game.input.MenuHandler;
import com.av.game.screen.util.ScreenEnum;
import com.av.game.screen.util.ScreenManager;

public class HeliGame extends com.badlogic.gdx.Game {
	@Override
	public void create () {
		//Initialize input observer
		InputObserver.createInstance();

		//Initialize end menu input handler
		Input.end_menu_handler = new MenuHandler();

		//Start game by initializing Screen
        ScreenManager.getInstance().initialize(this);

        //Create (or recreate) GameRenderer
        GameRenderer.createInstance();

        ScreenManager.getInstance().showScreen(ScreenEnum.GAME);
	}

    @Override
    public void dispose() {
        super.dispose();
        GameRenderer.dispose();
    }
}
