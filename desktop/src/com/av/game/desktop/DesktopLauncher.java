package com.av.game.desktop;

import com.av.game.HeliGame;
import com.av.game.desktop.input.HeliHandler;
import com.av.game.input.InputHandler;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		InputHandler[] handlers = new InputHandler[1];
		handlers[0] = new HeliHandler();
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		//config.setFromDisplayMode(LwjglApplicationConfiguration.getDesktopDisplayMode());
		//If I want to test windowed
		/*boolean fullscreen = false;
		if(!fullscreen ){
			config.fullscreen = false;
			config.width /= 1.2f;
			config.height /= 1.2f;
		}*/
		//config.fullscreen = true;
		config.resizable = false;
		config.samples = 4;
		config.vSyncEnabled = true;

		new LwjglApplication(new HeliGame(handlers), config);
	}
}
