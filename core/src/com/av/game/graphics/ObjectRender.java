package com.av.game.graphics;

import com.av.game.logic.object.GameObject;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;

public class ObjectRender implements Renderable {
    GameObject game_object;

    float scale_x;
    float scale_y;
    float rotation;

    public ObjectRender(GameObject game_object) {
        this.game_object = game_object;

        scale_x = 1f;
        scale_y = 1f;
        rotation = 0f;
    }

    public GameObject getGameObject() {
        return game_object;
    }

    @Override
    public void render(float state_time, SpriteBatch batch) {

    }

    @Override
    public void shapeRender(ShapeRenderer shape_renderer) {
        Polygon objPolygon = game_object.getCollision();
        shape_renderer.polygon(objPolygon.getTransformedVertices());
    }

    public float getScaleX() {
        return scale_x;
    }

    public void setScaleX(float scale_x) {
        this.scale_x = scale_x;
    }

    public float getScaleY() {
        return scale_y;
    }

    public void setScaleY(float scale_y) {
        this.scale_y = scale_y;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }
}
