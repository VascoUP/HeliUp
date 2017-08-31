package com.av.game.gui;

import com.av.game.gui.button.Button;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import java.util.LinkedList;
import java.util.List;

public abstract class UI {
    public static float width = -1f;
    public static float height = 780f;
    public static OrthographicCamera camera = null;
    public static BitmapFont font = null;

    private List<Button> buttons;

    UI() {
        if(width == -1f)
            width = height * (Gdx.graphics.getWidth() / (float)Gdx.graphics.getHeight());

        if(camera == null) {
            camera = new OrthographicCamera(width, height);
            camera.position.set(width / 2f, height / 2f, 0);
            camera.update();
        }

        if(font == null) {
            FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Xeron.ttf"));
            FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
            parameter.size = 40;
            parameter.characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789:!";
            font = generator.generateFont(parameter);
            font.setColor(Color.WHITE);
            generator.dispose();
        }

        this.buttons = new LinkedList<Button>();
    }

    public List<Button> getButtons() {
        return this.buttons;
    }

    public void addButton(Button button) {
        this.buttons.add(button);
    }

    public void render(SpriteBatch batch) {
        UI.camera.update();
        batch.setProjectionMatrix(UI.camera.combined);
        for(Button button : buttons)
            button.render(batch);
    }
}
