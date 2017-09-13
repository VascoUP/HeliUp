package com.av.game.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class AssetManager {
    private static AssetManager instance;

    public final float ui_width;
    public final float ui_height;
    public final OrthographicCamera ui_camera;
    public final BitmapFont ui_font;

    public TextureRegion[] default_button_texture;

    public final Texture helicopter_texture;
    public final Texture refuel_texture;
    public final Texture building_texture;
    public final Texture item_texture;
    public final Texture cloud_texture;

    private AssetManager() {
        ui_height = 780f;
        ui_width = ui_height * (Gdx.graphics.getWidth() / (float)Gdx.graphics.getHeight());

        ui_camera = new OrthographicCamera(ui_width, ui_height);
        ui_camera.position.set(ui_width / 2f, ui_height / 2f, 0);
        ui_camera.update();

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Xeron.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 40;
        parameter.characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789:!.";
        ui_font = generator.generateFont(parameter);
        ui_font.setColor(Color.WHITE);
        generator.dispose();

        helicopter_texture = new Texture(Gdx.files.internal("helicopter.png"));
        refuel_texture = new Texture(Gdx.files.internal("gasoline.png"));
        building_texture = new Texture(Gdx.files.internal("building.png"));
        item_texture = new Texture(Gdx.files.internal("item.png"));
        cloud_texture = new Texture(Gdx.files.internal("cloud.png"));
    }

    public static void createInstance() {
        instance = new AssetManager();
    }

    public static AssetManager getInstance() {
        return instance == null ? (instance = new AssetManager()) : instance;
    }

    public void dispose() {
        ui_font.dispose();
        helicopter_texture.dispose();
        refuel_texture.dispose();
        building_texture.dispose();
        item_texture.dispose();
        cloud_texture.dispose();
    }

}
