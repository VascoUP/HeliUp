package com.av.game.gui;

import com.av.game.gui.button.Button;
import com.av.game.screen.screen.PauseMenu;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class PauseMenuUI extends UI {
    private GlyphLayout layout;

    public PauseMenuUI(final PauseMenu screen, final int score) {
        super();

        this.layout = new GlyphLayout(UI.font, "SCORE: " + score);

        //Set up a button to replay
        Button play = new Button(new Vector2(UI.width / 2f - 125f, UI.height / 2f), new Vector2(250f, 150f));
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
                screen.replay();
                return true;
            }
        });
        play.setLabel("PLAY");
        addButton(play);

        //Set up a button to replay
        Button exit = new Button(new Vector2(UI.width / 2f - 125f, UI.height / 2f - 200f), new Vector2(250f, 150f));
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
                Gdx.app.exit();
                return true;
            }
        });
        exit.setLabel("EXIT");
        addButton(exit);
    }

    public void render(SpriteBatch batch) {
        super.render(batch);
        UI.font.draw(batch, layout, UI.width / 2f - layout.width / 2f, UI.height - 40f + layout.height / 2f);
    }
}