package com.av.game;

import android.os.Bundle;

import com.av.game.input.Input;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Input.game_handler = new HeliHandler();
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new HeliGame(), config);
	}
}
