package com.av.game.screen.screen;

import com.av.game.graphics.GameRenderer;
import com.av.game.input.Input;
import com.av.game.input.InputObserver;
import com.av.game.logic.Game;
import com.av.game.logic.object.ObjectsNotifier;
import com.av.game.screen.util.ScreenEnum;
import com.av.game.screen.util.ScreenManager;

public class GameScreen extends AbstractScreen {
    @Override
    public void buildStage() {
        Game.createInstance();
        GameRenderer.createInstance();
    }

    @Override
    public void render(float delta) {
        InputObserver.getInstance().handleInput();
        Game.getGame().update(delta);
        GameRenderer.getInstance().render(delta);
        if (Game.getGame().isGameOver())
            end();
    }

    @Override
    public void show() {
        InputObserver.clear();
        InputObserver.addInputListenner(Input.game_handler);
        Game.getGame().create();
        GameRenderer.clear();
        GameRenderer.getInstance().objectCreated(Game.getGame().getHelicopter());
        ObjectsNotifier.addObserver(GameRenderer.getInstance());
    }

    private void end() {
        ScreenManager.getInstance().showScreen(ScreenEnum.ENDMENU);
    }

    @Override
    public void dispose () {
        GameRenderer.dispose();
    }
}
