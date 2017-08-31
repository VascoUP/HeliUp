package com.av.game.gui.button;

import com.av.game.gui.ElementAction;
import com.av.game.gui.UI;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Button implements ElementAction {
    public enum ButtonState {
        NORMAL {
            public int getTextureRegion() {
                return 0;
            }
        }, PRESSED {
            public int getTextureRegion() {
                return 1;
            }

        }, HOVER {
            public int getTextureRegion() {
                return 2;
            }
        };
        public abstract int getTextureRegion();
    };

    private ButtonState buttonState;

    private TextureRegion[] texture;
    private Vector2 position;
    private Vector2 size;
    private Rectangle rectangle;

    private String label;
    private GlyphLayout layout;

    private ElementAction action;

    public Button() {
        this.buttonState = ButtonState.NORMAL;
        this.position = new Vector2(0, 0);
        this.size = new Vector2(500, 300);
        this.rectangle = new Rectangle(this.position.x, this.position.y, this.size.x, this.size.y);
        this.texture = ButtonLoader.loadButtonTexture();
        this.action = null;
        this.label = "";
        this.layout = new GlyphLayout(UI.font, label);
    }

    public Button(Vector2 position) {
        this.buttonState = ButtonState.NORMAL;
        this.position = position;
        this.size = new Vector2(500, 300);
        this.rectangle = new Rectangle(this.position.x, this.position.y, this.size.x, this.size.y);
        this.texture = ButtonLoader.loadButtonTexture();
        this.action = null;
        this.label = "";
        this.layout = new GlyphLayout(UI.font, label);
    }

    public Button(Vector2 position, Vector2 size) {
        this.buttonState = ButtonState.NORMAL;
        this.position = position;
        this.size = size;
        this.rectangle = new Rectangle(this.position.x, this.position.y, this.size.x, this.size.y);
        this.texture = ButtonLoader.loadButtonTexture();
        this.action = null;
        this.label = "";
        this.layout = new GlyphLayout(UI.font, label);
    }


    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getSize() {
        return size;
    }

    public ButtonState getButtonState() {
        return buttonState;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
        this.rectangle = new Rectangle(this.position.x, this.position.y, this.size.x, this.size.y);
    }

    public void setSize(Vector2 size) {
        this.size = size;
        this.rectangle = new Rectangle(this.position.x, this.position.y, this.size.x, this.size.y);
    }

    public void setTexture(String path) {
        this.texture = ButtonLoader.loadButtonTexture(path);
    }

    public void setTexture(String path, int cols, int lins) {
        this.texture = ButtonLoader.loadButtonTexture(path, cols, lins);
    }

    public void setAction(ElementAction action) {
        this.action = action;
    }

    public void setState(ButtonState buttonState) {
        this.buttonState = buttonState;
    }

    public void setLabel(String label) {
        this.label = label;
        this.layout = new GlyphLayout(UI.font, label);
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture[buttonState.getTextureRegion()], position.x, position.y, size.x, size.y);
        UI.font.draw(batch, layout, position.x + (size.x - layout.width) / 2f, position.y + (size.y + layout.height) / 2f);
    }


    @Override
    public boolean onHover() {
        return action != null && action.onHover();
    }

    @Override
    public boolean onPress() {
        return action != null && action.onPress();
    }

    @Override
    public boolean onRelease() {
        return action != null && action.onRelease();
    }
}
