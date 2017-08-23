package com.av.game.logic.object;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class GameObject implements Comparable {
    private static String TAG = "GameObject";

    private Vector2 position;
    private float rotation;
    private Polygon collision;

    public GameObject (Vector2 position, Polygon polygon) {
        this.position = position;
        this.rotation = 0;
        this.collision = polygon;
    }

    public GameObject (Vector2 position, float rotation, Polygon polygon) {
        this.position = position;
        this.rotation = rotation;
        this.collision = polygon;
        Rectangle boundingRectangle = this.collision.getBoundingRectangle();
        this.collision.setOrigin(boundingRectangle.getWidth() / 2f, boundingRectangle.getHeight() / 2f);
    }

    public Vector2 getPosition() {
        return this.position;
    }

    public float getRotation() {
        return this.rotation;
    }

    public Polygon getCollision() {
        this.collision.setRotation(0);
        this.collision.setPosition(0,0);
        this.collision.rotate(this.rotation);
        this.collision.translate(position.x, position.y);
        return this.collision;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public void destroy() {}

    @Override
    public int compareTo(Object o) {
        return (int)position.x;
    }
}
