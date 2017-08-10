package com.av.game.logic.object;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;

import java.awt.geom.Point2D;

public class GameObject {
    private Vector2 position;
    protected Polygon collision;

    public GameObject (Vector2 position, Polygon polygon) {
        this.position = position;
        this.collision = polygon;
    }

    public Vector2 getPosition() {
        return this.position;
    }

    public Polygon getCollision() {
        return this.collision;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void destroy() {}
}
