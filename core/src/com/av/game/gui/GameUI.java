package com.av.game.gui;

import com.av.game.assets.AssetManager;
import com.av.game.logic.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameUI extends UI {
    private Game game;

    private final float FUEL_X = 20f;
    private final float FUEL_Y = 60f;
    private final float KM_X = 20f;
    private final float KM_Y = AssetManager.getInstance().ui_height - 60f;

    public GameUI(Game game) {
        super();
        this.game = game;
    }

    public void render(SpriteBatch batch) {
        super.render(batch);
        AssetManager.getInstance().ui_font.draw(batch, "KM:" + (int)(game.getHelicopter().getPosition().x/100), KM_X, KM_Y);
        AssetManager.getInstance().ui_font.draw(batch, "FUEL:" + game.getHelicopter().getHeliFuel().getFuel(), FUEL_X, FUEL_Y);
    }
}
