package com.av.game.screen.screen;

import com.av.game.graphics.GameRenderer;
import com.av.game.gui.GameUI;
import com.av.game.input.Input;
import com.av.game.input.InputObserver;
import com.av.game.logic.Game;
import com.av.game.logic.object.ObjectsNotifier;
import com.av.game.logic.physics.CollisionNotifier;
import com.av.game.logic.physics.Physics;
import com.av.game.screen.util.ScreenEnum;
import com.av.game.screen.util.ScreenManager;

public class GameScreen extends AbstractScreen {
    @Override
    public void buildStage() {
        //Create (or recreate) a new instance of game
        if(Game.getGame() == null) {
            Game.createInstance();
            //Create all Game's objects
            Game.getGame().create();
        }
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

    @Override
    public void show() {
        //Clear previous screen input handlers
        InputObserver.clear();
        //And this Screen's input handler
        InputObserver.addInputListenner(Input.game_handler);

        //Change UI
        GameRenderer.getInstance().setUI(new GameUI(Game.getGame()));

        //if(Game.getGame().isGameOver()) {
            //Create all Game's objects
            Game.getGame().create();

            //Reset GameRenderer
            GameRenderer.clear();
            GameRenderer.getInstance().objectCreated(Game.getGame().getHelicopter());

            //Add GameRenderer as a Object observer
            ObjectsNotifier.addObserver(GameRenderer.getInstance());
            ObjectsNotifier.addObserver(CollisionNotifier.getInstance());
            ObjectsNotifier.addObserver(Physics.getInstance());
        //}
    }

    private void end() {
        //Reset UI
        GameRenderer.getInstance().setUI(null);

        //On game over go to End Menu
        ScreenManager.getInstance().showScreen(ScreenEnum.ENDMENU);
    }

    @Override
    public void dispose () {}
}
