package com.av.game;

import android.os.Bundle;

import com.av.game.input.InputHandler;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		InputHandler[] handlers = new InputHandler[1];
		handlers[0] = new HeliHandler();
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new HeliGame(handlers), config);
	}
}
