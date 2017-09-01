package com.av.game.screen.screen;

import com.av.game.graphics.GameRenderer;
import com.av.game.input.Input;
import com.av.game.input.InputObserver;
import com.av.game.logic.Game;
import com.av.game.screen.ads.Device;
import com.av.game.screen.util.ScreenEnum;
import com.av.game.screen.util.ScreenManager;

public class AdScreen extends AbstractScreen {
    private boolean ad_load_error;
    private boolean reward_player;
    private boolean request_ad;
    private boolean ad_closed;

    @Override
    public void buildStage() {
        ad_load_error = false;
        reward_player = false;
        request_ad = false;
        ad_closed = false;
        GameRenderer.getInstance().setUI(null);
    }

    @Override
    public void show() {
        //Clear previous screen input handlers
        InputObserver.clear();
        //And this Screen's input handler
        InputObserver.addInputListenner(Input.gui_handler);
    }

    @Override
    public void render(float delta) {
        if(!Device.showAndroidAd || Game.getGame().getRetried()) {
            ScreenManager.getInstance().showScreen(ScreenEnum.PAUSE_MENU);
            return;
        } else if(!request_ad) {
            Device.app.showOrLoadInterstital(this);
            request_ad = true;
        }

        GameRenderer.getInstance().render(0f);

        if(reward_player) {
            Game.getGame().retry();
            GameRenderer.clear();
            GameRenderer.getInstance().objectCreated(Game.getGame().getHelicopter());

            ScreenManager.getInstance().showScreen(ScreenEnum.COUNT_DOWN);
        } else if(ad_closed || ad_load_error)
            ScreenManager.getInstance().showScreen(ScreenEnum.PAUSE_MENU);
    }

    @Override
    public void dispose () {}

    public void setAdLoadError() {
        ad_load_error = true;
    }

    public void setRewardPlayer() {
        reward_player = true;
    }

    public void setAdClosed() {
        ad_closed = true;
    }
}
