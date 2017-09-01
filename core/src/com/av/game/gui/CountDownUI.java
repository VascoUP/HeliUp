package com.av.game.gui;

import com.av.game.assets.AssetManager;
import com.av.game.screen.screen.CountDownScreen;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CountDownUI extends UI {
    private CountDownScreen screen;
    private GlyphLayout layout;

    public CountDownUI(final CountDownScreen screen) {
        super();
        this.screen = screen;
        this.layout = new GlyphLayout(AssetManager.getInstance().ui_font, (int)screen.getCountDown() + "");
    }

    public void render(SpriteBatch batch) {
        super.render(batch);

        String tag = "";

        //Get counter
        int count_down = (int)screen.getCountDown();
        if(count_down < 1f)
            tag = "GO!";
        else
            tag = count_down + "";

        //Reset layout
        this.layout.setText(AssetManager.getInstance().ui_font, tag);

        AssetManager.getInstance().ui_font.draw(batch, layout, AssetManager.getInstance().ui_width / 2f - layout.width / 2f, AssetManager.getInstance().ui_height - 40f + layout.height / 2f);
    }
}
