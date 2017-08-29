package com.av.game;

import com.av.game.input.Input;
import com.av.game.input.InputObserver;
import com.av.game.input.MenuHandler;
import com.av.game.screen.util.ScreenEnum;
import com.av.game.screen.util.ScreenManager;

public class HeliGame extends com.badlogic.gdx.Game {
	@Override
	public void create () {
		InputObserver.createInstance();

		ScreenManager.getInstance().initialize(this);
		ScreenManager.getInstance().showScreen(ScreenEnum.GAME);

		Input.end_menu_handler = new MenuHandler(this);
	}
}
