package com.av.game.gui;

import com.av.game.logic.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class GameUI {
    private BitmapFont font;
    private Game game;

    private static float UI_WIDTH;
    private static final float UI_HEIGHT = 780;

    private static float FUEL_X = 20;
    private static float FUEL_Y = 60;
    private static float KM_X = 20;
    private static float KM_Y = UI_HEIGHT - 60;

    private OrthographicCamera cam;

    public GameUI(Game game) {
        this.game = game;

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Xeron.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 40;
        parameter.characters = "FUELKM0123456789:";

        font = generator.generateFont(parameter);
        font.setColor(Color.WHITE);
        generator.dispose();


        UI_WIDTH = UI_HEIGHT * (Gdx.graphics.getWidth() / (float)Gdx.graphics.getHeight());

        cam = new OrthographicCamera(UI_WIDTH, UI_HEIGHT);
        cam.position.set(UI_WIDTH / 2f, UI_HEIGHT / 2f, 0);
        cam.update();

    }

    public void render(SpriteBatch batch) {
        cam.update();
        batch.setProjectionMatrix(cam.combined);
        font.draw(batch, "KM:" + (int)(game.getHelicopter().getPosition().x/100), KM_X, KM_Y);
        font.draw(batch, "FUEL:" + game.getHelicopter().getHeliFuel().getFuel(), FUEL_X, FUEL_Y);
    }
}
