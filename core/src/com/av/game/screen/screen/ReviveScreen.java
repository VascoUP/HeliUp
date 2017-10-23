package com.av.game.screen.screen;

import com.av.game.file.Record;
import com.av.game.graphics.GameRenderer;
import com.av.game.gui.ReviveUI;
import com.av.game.input.Input;
import com.av.game.input.InputObserver;
import com.av.game.logic.Game;
import com.av.game.screen.ads.Device;
import com.av.game.screen.util.ScreenEnum;
import com.av.game.screen.util.ScreenManager;

public class ReviveScreen extends AbstractScreen {
    private boolean to_pause;
    private boolean to_ad;

    @Override
    public void buildStage() {
        this.to_ad = false;
        this.to_pause = false;
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
        GameRenderer.getInstance().setUI(new ReviveUI(this, high_score, score));
    }

    @Override
    public void render(float delta) {
        if(!Device.showAndroidAd || Game.getGame().getRetried()) {
            Game.getGame().getHelicopter().end();
            ScreenManager.getInstance().showScreen(ScreenEnum.END_GAME);
            return;
        }

        GameRenderer.getInstance().render(0f);

        //Handle input
        InputObserver.getInstance().handleInput();

        if(to_ad)
            ScreenManager.getInstance().showScreen(ScreenEnum.AD_SCREEN);
        else if(to_pause) {
            Game.getGame().getHelicopter().end();
            ScreenManager.getInstance().showScreen(ScreenEnum.END_GAME);
        }
    }

    @Override
    public void dispose () {}

    public void setToPause() {
        this.to_pause = true;
    }

    public void setToAd() {
        this.to_ad = true;
    }
}
