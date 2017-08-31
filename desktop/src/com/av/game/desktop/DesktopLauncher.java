package com.av.game.desktop;

import com.av.game.HeliGame;
import com.av.game.input.Input;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Input.gui_handler = new GUIHandler();
		Input.game_handler = new HeliHandler();
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.resizable = false;
		config.samples = 4;
		new LwjglApplication(new HeliGame(), config);
	}
}
