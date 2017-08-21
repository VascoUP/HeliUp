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

    public static float UI_WIDTH;
    public static final float UI_HEIGHT = 780;

    private OrthographicCamera cam;

    public GameUI(Game game) {
        this.game = game;

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Xeron.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 40;
        parameter.characters = "FUEL0123456789:";

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
        font.draw(batch, "FUEL:" + game.getHelicopter().getHeliFuel().getFuel(), 20, 60);
    }
}
