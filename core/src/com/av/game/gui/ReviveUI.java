package com.av.game.gui;

import com.av.game.assets.AssetManager;
import com.av.game.gui.button.Button;
import com.av.game.screen.screen.ReviveScreen;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class ReviveUI extends UI {
    private GlyphLayout layout;

    public ReviveUI(final ReviveScreen screen, final int score) {
        super();

        this.layout = new GlyphLayout(AssetManager.getInstance().ui_font, "SCORE: " + score);

        //Set up a button to replay
        Button revive = new Button(new Vector2(AssetManager.getInstance().ui_width / 2f - 125f, AssetManager.getInstance().ui_height / 2f), new Vector2(250f, 150f));
        revive.setAction(new ElementAction() {
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
                //screen.showAd();
                screen.setToAd();
                return true;
            }
        });
        revive.setLabel("REVIVE");
        addButton(revive);

        //Set up a button to replay
        Button quit = new Button(new Vector2(AssetManager.getInstance().ui_width / 2f - 125f, AssetManager.getInstance().ui_height / 2f - 200f), new Vector2(250f, 150f));
        quit.setAction(new ElementAction() {
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
                //Gdx.app.exit();
                screen.setToPause();
                return true;
            }
        });
        quit.setLabel("NO.");
        addButton(quit);
    }

    public void render(SpriteBatch batch) {
        super.render(batch);
        AssetManager.getInstance().ui_font.draw(batch, layout, AssetManager.getInstance().ui_width / 2f - layout.width / 2f, AssetManager.getInstance().ui_height - 40f + layout.height / 2f);
    }
}
