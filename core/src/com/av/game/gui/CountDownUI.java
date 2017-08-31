package com.av.game.gui;

import com.av.game.screen.screen.CountDownScreen;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CountDownUI extends UI {
    private CountDownScreen screen;
    private GlyphLayout layout;

    public CountDownUI(final CountDownScreen screen) {
        super();
        this.screen = screen;
        this.layout = new GlyphLayout(UI.font, (int)screen.getCountDown() + "");
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
        this.layout.setText(UI.font, tag);

        UI.font.draw(batch, layout, UI.width / 2f - layout.width / 2f, UI.height - 40f + layout.height / 2f);
    }
}
