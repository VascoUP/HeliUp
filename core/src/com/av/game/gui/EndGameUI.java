package com.av.game.gui;

import com.av.game.assets.AssetManager;
import com.av.game.gui.button.Button;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class EndGameUI extends UI {
    private GlyphLayout layout;

    private static final float ACCELERATION = -0.6f;

    public boolean animation_over = false;

    private final float PLAY_Y;
    private final float EXIT_Y;
    private final float LABEL_Y;

    private Button play;
    private Button exit;

    private float velocity;
    private float label_y;

    public EndGameUI(final int score) {
        super();

        this.layout = new GlyphLayout(AssetManager.getInstance().ui_font, "SCORE: " + score);

        this.PLAY_Y = AssetManager.getInstance().ui_height / 2f;
        this.EXIT_Y = AssetManager.getInstance().ui_height / 2f - 200f;
        this.LABEL_Y = AssetManager.getInstance().ui_height - 40f + layout.height / 2f;

        //Set up a button to replay
        play = new Button(new Vector2(AssetManager.getInstance().ui_width / 2f - 125f, AssetManager.getInstance().ui_height + 400f), new Vector2(250f, 150f));
        play.setAction(new ElementAction() {
            @Override
            public boolean onHover() {
                return false;
            }

            @Override
            public boolean onPress() {
                return false;
            }

            @Override
            public boolean onRelease() {
                return false;
            }
        });
        play.setLabel("PLAY");
        addButton(play);

        //Set up a button to replay
        exit = new Button(new Vector2(AssetManager.getInstance().ui_width / 2f - 125f, AssetManager.getInstance().ui_height + 200f), new Vector2(250f, 150f));
        exit.setAction(new ElementAction() {
            @Override
            public boolean onHover() {
                return false;
            }

            @Override
            public boolean onPress() {
                return false;
            }

            @Override
            public boolean onRelease() {
                return false;
            }
        });
        exit.setLabel("QUIT");
        addButton(exit);

        velocity = 0f;
        label_y = AssetManager.getInstance().ui_height * 3/2f + layout.height / 2f + 360f;
    }

    public void render(SpriteBatch batch) {
        super.render(batch);
        AssetManager.getInstance().ui_font.draw(batch, layout, AssetManager.getInstance().ui_width / 2f - layout.width / 2f, label_y);

        velocity += ACCELERATION;
        if(play.getPosition().y > PLAY_Y)
            play.setPosition(new Vector2(play.getPosition().x, play.getPosition().y + velocity));
        if(exit.getPosition().y > EXIT_Y)
            exit.setPosition(new Vector2(exit.getPosition().x, exit.getPosition().y + velocity));
        if(label_y > LABEL_Y)
            label_y += velocity;

        if(play.getPosition().y <= PLAY_Y && exit.getPosition().y <= EXIT_Y && label_y <= LABEL_Y)
            animation_over = true;
    }
}
