package com.av.game.graphics;

import com.av.game.logic.object.GameObject;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;

public class ObjectRender implements Renderable {
    GameObject gameObject;

    float scale_x;
    float scale_y;
    float rotation;

    public ObjectRender(GameObject gameObject) {
        this.gameObject = gameObject;

        scale_x = 1f;
        scale_y = 1f;
        rotation = 0f;
    }

    public GameObject getGameObject() {
        return gameObject;
    }

    @Override
    public void render(float stateTime, SpriteBatch batch) {

    }

    @Override
    public void shapeRender(ShapeRenderer shapeRenderer) {
        Polygon objPolygon = gameObject.getCollision();
        shapeRenderer.polygon(objPolygon.getTransformedVertices());
    }

    public float getScale_x() {
        return scale_x;
    }

    public void setScale_x(float scale_x) {
        this.scale_x = scale_x;
    }

    public float getScale_y() {
        return scale_y;
    }

    public void setScale_y(float scale_y) {
        this.scale_y = scale_y;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }
}
