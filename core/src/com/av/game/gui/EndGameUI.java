package com.av.game.gui;

import com.av.game.assets.AssetManager;
import com.av.game.gui.button.Button;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class EndGameUI extends UI {
    private GlyphLayout score_layout;
    private GlyphLayout high_score_layout;

    private static final float ACCELERATION = -0.6f;

    public boolean animation_over = false;

    private final float PLAY_Y;

    private Button play;
    private Button exit;

    private float velocity;
    private float score_label_y;
    private float high_score_label_y;

    public EndGameUI(final int high_score, final int score) {
        super();

        this.score_layout = new GlyphLayout(AssetManager.getInstance().ui_font, "SCORE: " + score);
        this.high_score_layout = new GlyphLayout(AssetManager.getInstance().ui_font, "HIGH SCORE: " + high_score);

        this.PLAY_Y = AssetManager.getInstance().ui_height / 2f;

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
        score_label_y = AssetManager.getInstance().ui_height * 3/2f + score_layout.height / 2f + 360f;
        high_score_label_y = AssetManager.getInstance().ui_height * 3/2f + score_layout.height / 2f + 300f;
    }

    public void render(SpriteBatch batch) {
        super.render(batch);
        AssetManager.getInstance().ui_font.draw(batch, score_layout, AssetManager.getInstance().ui_width / 2f - score_layout.width / 2f, score_label_y);
        AssetManager.getInstance().ui_font.draw(batch, high_score_layout, AssetManager.getInstance().ui_width / 2f - high_score_layout.width / 2f, high_score_label_y);

        //Don'y update if animation is over
        if(animation_over)
           return;

        //Update velocity
        velocity += ACCELERATION;

        //Update positions
        play.setPosition(new Vector2(play.getPosition().x, play.getPosition().y + velocity));
        exit.setPosition(new Vector2(exit.getPosition().x, exit.getPosition().y + velocity));
        score_label_y += velocity;
        high_score_label_y += velocity;

        //Check if animation is over
        if(play.getPosition().y <= PLAY_Y)
            animation_over = true;
    }
}
