package com.av.game.screen.screen;

import com.av.game.graphics.GameRenderer;
import com.av.game.gui.GameUI;
import com.av.game.input.Input;
import com.av.game.input.InputObserver;
import com.av.game.logic.Game;
import com.av.game.logic.object.ObjectsNotifier;
import com.av.game.logic.physics.CollisionNotifier;
import com.av.game.logic.physics.Physics;
import com.av.game.screen.ads.Device;
import com.av.game.screen.util.ScreenEnum;
import com.av.game.screen.util.ScreenManager;
import com.badlogic.gdx.Gdx;

public class GameScreen extends AbstractScreen {
    private static String TAG = "GameScreen";

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
        InputObserver.addInputListenner(Input.game_handler);

        //Change UI
        GameRenderer.getInstance().setUI(new GameUI(Game.getGame()));

        //Add Object observers
        ObjectsNotifier.addObserver(GameRenderer.getInstance());
        ObjectsNotifier.addObserver(CollisionNotifier.getInstance());
        ObjectsNotifier.addObserver(Physics.getInstance());
    }

    @Override
    public void render(float delta) {
        //Handle input
        InputObserver.getInstance().handleInput();

        //Update game
        Game.getGame().update();

        //Render frame
        GameRenderer.getInstance().render(delta);

        //End game if lost
        if (Game.getGame().isGameOver())
            end();
    }

    private void end() {
        //On game over go to End Menu
        Gdx.app.log(TAG, Device.showAndroidAd + " - show?");
        if(Device.showAndroidAd)
            Device.app.showOrLoadInterstital();
        ScreenManager.getInstance().showScreen(ScreenEnum.PAUSE_MENU);
    }

    @Override
    public void dispose () {}
}
