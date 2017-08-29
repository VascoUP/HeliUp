package com.av.game.screen.util;

import com.av.game.screen.screen.AbstractScreen;
import com.av.game.screen.screen.EndGameScreen;
import com.av.game.screen.screen.GameScreen;

public enum ScreenEnum {
    GAME {
        public AbstractScreen getScreen(Object... params) {
            return new GameScreen();
        }
    },
    ENDMENU {
        public AbstractScreen getScreen(Object... params) { return new EndGameScreen(); }
    };

    public abstract AbstractScreen getScreen(Object... params);
}
