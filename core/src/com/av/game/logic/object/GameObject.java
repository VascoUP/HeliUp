package com.av.game.logic.object;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class GameObject implements Comparable {
    protected Vector2 position;
    private float rotation;
    private Polygon collision;

    private void constructor(Vector2 position, Polygon polygon) {
        this.position = position;
        this.collision = polygon;

        //Set origin to the center of the object
        Rectangle boundingRectangle = this.collision.getBoundingRectangle();
        this.collision.setOrigin(boundingRectangle.getWidth() / 2f, boundingRectangle.getHeight() / 2f);
    }

    public GameObject (Vector2 position, Polygon polygon) {
        constructor(position, polygon);
        this.rotation = 0;
    }

    public GameObject (Vector2 position, float rotation, Polygon polygon) {
        constructor(position, polygon);
        this.rotation = rotation;
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

    protected void setRotation(float rotation) {
        this.rotation = rotation;
    }

    @Override
    public int compareTo(Object o) {
        return (int)position.x;
    }
}
