package com.av.game.screen.screen;

import com.av.game.file.Record;
import com.av.game.graphics.GameRenderer;
import com.av.game.gui.EndGameUI;
import com.av.game.input.Input;
import com.av.game.input.InputObserver;
import com.av.game.logic.Game;
import com.av.game.screen.util.ScreenEnum;
import com.av.game.screen.util.ScreenManager;

public class EndGameScreen extends AbstractScreen {

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

        //Calculate new high score
        int high_score = Record.getHighScore();
        int score = (int) Game.getGame().getHelicopter().getPosition().x/100;
        if(score > high_score) {
            high_score = score;
            Record.setHighScore(score);
        }

        //Change UI
        GameRenderer.getInstance().setUI(new EndGameUI(high_score, score));
    }

    @Override
    public void render(float delta) {
        Game.getGame().getHelicopter().update();
        GameRenderer.getInstance().render(0f);

        if(((EndGameUI)GameRenderer.getInstance().getUI()).animation_over && Game.getGame().getHelicopter().getPosition().y < -100f)
            ScreenManager.getInstance().showScreen(ScreenEnum.PAUSE_MENU);
    }

    @Override
    public void dispose () {}
}
