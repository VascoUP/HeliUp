package com.av.game.gui;

import com.av.game.assets.AssetManager;
import com.av.game.gui.button.Button;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.LinkedList;
import java.util.List;

public abstract class UI {
    private List<Button> buttons;

    UI() {
        this.buttons = new LinkedList<Button>();
    }

    public List<Button> getButtons() {
        return this.buttons;
    }

    public void addButton(Button button) {
        this.buttons.add(button);
    }

    public void render(SpriteBatch batch) {
        AssetManager.getInstance().ui_camera.update();
        batch.setProjectionMatrix(AssetManager.getInstance().ui_camera.combined);
        for(Button button : buttons)
            button.render(batch);
    }
}
