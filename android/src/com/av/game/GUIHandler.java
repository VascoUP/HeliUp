package com.av.game;

import com.av.game.assets.AssetManager;
import com.av.game.graphics.GameRenderer;
import com.av.game.gui.UI;
import com.av.game.gui.button.Button;
import com.av.game.input.InputHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

public class GUIHandler implements InputHandler {
    private enum InputState {PRESSING, RELEASED, NORMAL};
    private InputState inputState;

    public GUIHandler() {
        inputState = InputState.NORMAL;
    }

    @Override
    public void handleInput() {
        //Get current ui from GameRenderer
        GameRenderer renderer = GameRenderer.getInstance();
        if(renderer == null)
            return;
        UI ui = renderer.getUI();
        if(ui == null)
            return;

        //Update the current inputState
        updateState();

        //Iterate through buttons
        boolean end = false;
        List<Button> buttonList = ui.getButtons();
        for(Button button : buttonList) {
            Rectangle rectangle = button.getRectangle();
            if (inputState == InputState.PRESSING) {
                //Get pointer position
                Vector2 pointer = calculatePointer();
                if (rectangle.contains(pointer.x, pointer.y)) {
                    button.setState(Button.ButtonState.PRESSED);
                    if (button.onPress())
                        break;
                    continue;
                }
            } else if(inputState == InputState.RELEASED)
                if (button.getButtonState() == Button.ButtonState.PRESSED)
                    end = button.onRelease();
            button.setState(Button.ButtonState.NORMAL);
            if (end)
                break;
        }
    }

    private void updateState() {
        if (Gdx.input.isTouched()) {
            if (inputState != InputState.PRESSING)
                inputState = InputState.PRESSING;
        } else if (inputState == InputState.PRESSING)
            inputState = InputState.RELEASED;
        else if (inputState == InputState.RELEASED)
            inputState = InputState.NORMAL;
    }

    private Vector2 calculatePointer() {
        float calc_x = AssetManager.getInstance().ui_width / Gdx.graphics.getWidth();
        float calc_y = AssetManager.getInstance().ui_height / Gdx.graphics.getHeight();
        return new Vector2(Gdx.input.getX() * calc_x, AssetManager.getInstance().ui_height - Gdx.input.getY() * calc_y);
    }
}
