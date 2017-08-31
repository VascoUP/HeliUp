package com.av.game.screen.screen;

import com.av.game.graphics.GameRenderer;
import com.av.game.gui.PauseMenuUI;
import com.av.game.input.Input;
import com.av.game.input.InputObserver;
import com.av.game.logic.Game;
import com.av.game.screen.util.ScreenEnum;
import com.av.game.screen.util.ScreenManager;

public class PauseMenu extends AbstractScreen {
    private boolean replay = false;

    @Override
    public void buildStage() {
        GameRenderer.getInstance().setUI(null);
    }

    @Override
    public void show() {
        //Clear previous screen input handlers
        InputObserver.clear();
        //And this Screen's input handler
        InputObserver.addInputListenner(Input.gui_handler);
        InputObserver.addInputListenner(Input.end_menu_handler);

        //Change UI
        GameRenderer.getInstance().setUI(new PauseMenuUI(this, (int)Game.getGame().getHelicopter().getPosition().x/100));
    }

    @Override
    public void render(float delta) {
        GameRenderer.getInstance().render(0f);

        //Handle input
        InputObserver.getInstance().handleInput();

        if(replay) {
            if(Game.getGame().isGameOver()) {
                //Reset game
                Game.getGame().create();

                //Reset GameRenderer
                GameRenderer.clear();
                GameRenderer.getInstance().objectCreated(Game.getGame().getHelicopter());
            }

            ScreenManager.getInstance().showScreen(ScreenEnum.COUNT_DOWN);
        }
    }

    @Override
    public void dispose () {}

    public void replay() {
        replay = true;
    }
}
