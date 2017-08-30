package com.av.game.screen.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.ScreenUtils;

public class ScreenShot {
    public static void saveScreenShot() {
        Pixmap pixmap = screenShot();
        PixmapIO.writePNG(Gdx.files.local("mypixmap.png"), pixmap);
        pixmap.dispose();
    }

    public static Pixmap screenShot() {
        Gdx.app.log("ScreenShot", "Back buffer size " + Gdx.graphics.getBackBufferWidth() + " - " + Gdx.graphics.getBackBufferHeight());
        byte[] pixels = ScreenUtils.getFrameBufferPixels(0, 0, Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight(), true);
        Pixmap pixmap = new Pixmap(Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight(), Pixmap.Format.RGBA8888);
        BufferUtils.copy(pixels, 0, pixmap.getPixels(), pixels.length);
        return pixmap;
    }
}
