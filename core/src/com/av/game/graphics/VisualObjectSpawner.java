package com.av.game.graphics;

import com.av.game.assets.AssetManager;
import com.av.game.random.RandomFunctions;
import com.av.game.screen.util.ScreenInfo;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

public class VisualObjectSpawner {
    private float stateTime = 0f;

    private static float MAX_HEIGHT;
    private static float MIN_HEIGHT;

    private GameRenderer renderer;
    private Random random;

    public VisualObjectSpawner(GameRenderer renderer) {
        MAX_HEIGHT = ScreenInfo.height - 20f;
        MIN_HEIGHT = 100f;
        this.renderer = renderer;
        this.random = new Random();
    }

    void update() {
        updateZ0();
        updateZ2();
    }

    private void updateZ0() {
        if(random.nextInt(200) == 1) {
            Vector3 cam_position = GameRenderer.getCamPosition();
            Vector2 obj_position = new Vector2(cam_position.x + ScreenInfo.width, RandomFunctions.nextFloat(random, MAX_HEIGHT, MIN_HEIGHT));
            renderer.addZ0(new VisualObject(obj_position, new Vector2(25f, 0f), new Sprite(AssetManager.getInstance().cloud_texture), true));
        }
    }

    private void updateZ2() {
        if(random.nextInt(400) == 1) {
            Vector3 cam_position = GameRenderer.getCamPosition();
            Vector2 obj_position = new Vector2(cam_position.x + ScreenInfo.width, RandomFunctions.nextFloat(random, MAX_HEIGHT, MIN_HEIGHT));
            renderer.addZ2(new VisualObject(obj_position, new Vector2(-50f, 0f), new Sprite(AssetManager.getInstance().cloud_texture), false));
        }
    }
}
