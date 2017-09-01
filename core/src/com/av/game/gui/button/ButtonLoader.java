package com.av.game.gui.button;

import com.av.game.assets.AssetManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

class ButtonLoader {
    private static final String texture_path = "button.png";
    private static final int n_cols = 3;
    private static final int n_lins = 1;

    static TextureRegion[] loadButtonTexture() {
        return AssetManager.getInstance().default_button_texture == null ? loadButtonTexture(texture_path) : AssetManager.getInstance().default_button_texture;
    }

    static TextureRegion[] loadButtonTexture(String path) {
        return loadButtonTexture(path, n_cols, n_lins);
    }

    static TextureRegion[] loadButtonTexture(String path, int cols, int lins) {
        Texture texture = new Texture(Gdx.files.internal(path));
        TextureRegion[][] textures = TextureRegion.split(texture, texture.getWidth() / cols, texture.getHeight() / lins);

        TextureRegion[] button_textures = new TextureRegion[n_cols * n_lins];
        int index = 0;
        for (int i = 0; i < n_lins; i++)
            for (int j = 0; j < n_cols; j++)
                button_textures[index++] = textures[i][j];

        AssetManager.getInstance().default_button_texture = button_textures;

        return button_textures;
    }
}