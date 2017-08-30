package com.av.game.screen.util;

import com.av.game.screen.screen.AbstractScreen;
import com.av.game.screen.screen.EndGameScreen;
import com.av.game.screen.screen.GameScreen;

//Enum with all diferent screens
public enum ScreenEnum {
    //Game screen
    GAME {
        public AbstractScreen getScreen(Object... params) {
            return new GameScreen();
        }
    },
    //End menu screen
    ENDMENU {
        public AbstractScreen getScreen(Object... params) { return new EndGameScreen(); }
    };

    public abstract AbstractScreen getScreen(Object... params);
}
