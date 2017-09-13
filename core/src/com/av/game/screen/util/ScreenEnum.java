package com.av.game.screen.util;

import com.av.game.screen.screen.AbstractScreen;
import com.av.game.screen.screen.AdScreen;
import com.av.game.screen.screen.CountDownScreen;
import com.av.game.screen.screen.EndGameScreen;
import com.av.game.screen.screen.GameScreen;
import com.av.game.screen.screen.PauseMenu;
import com.av.game.screen.screen.ReviveScreen;

//Enum with all diferent screens
public enum ScreenEnum {
    //Game screen
    GAME {
        public AbstractScreen getScreen(Object... params) {
            return new GameScreen();
        }
    },
    //Count down screen
    COUNT_DOWN {
        public AbstractScreen getScreen(Object... params) {
            return new CountDownScreen();
        }
    },
    REVIVE_SCREEN {
        public AbstractScreen getScreen(Object... params) {
            return new ReviveScreen();
        }
    },
    AD_SCREEN {
        public AbstractScreen getScreen(Object... params) {
            return new AdScreen();
        }
    },
    END_GAME {
        public AbstractScreen getScreen(Object... params) {
            return new EndGameScreen();
        }
    },
    //End menu screen
    PAUSE_MENU {
        public AbstractScreen getScreen(Object... params) { return new PauseMenu(); }
    };

    public abstract AbstractScreen getScreen(Object... params);
}
