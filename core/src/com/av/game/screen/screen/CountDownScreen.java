package com.av.game.screen.screen;

import com.av.game.graphics.GameRenderer;
import com.av.game.gui.CountDownUI;
import com.av.game.input.InputObserver;
import com.av.game.screen.util.ScreenEnum;
import com.av.game.screen.util.ScreenManager;

public class CountDownScreen extends AbstractScreen {
    private float count_down = 4f;

    @Override
    public void buildStage() {
        GameRenderer.getInstance().setUI(null);
    }

    @Override
    public void show() {
        //Clear previous screen input handlers
        InputObserver.clear();

        //Change UI
        GameRenderer.getInstance().setUI(new CountDownUI(this));
    }

    @Override
    public void render(float delta) {
        //Draw game
        GameRenderer.getInstance().render(0f);

        //Update counter
        count_down -= delta;

        //Start game when count down gets to Zero
        if(count_down <= 0f)
            ScreenManager.getInstance().showScreen(ScreenEnum.GAME);
    }

    @Override
    public void dispose () {}

    public float getCountDown() {
        return count_down;
    }
}
