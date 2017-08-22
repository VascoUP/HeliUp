package com.av.game.logic.object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.StringBuilder;

import java.awt.geom.Point2D;

public class GameObject implements Comparable {
    private static String TAG = "GameObject";

    private Vector2 position;
    private Polygon collision;

    public GameObject (Vector2 position, Polygon polygon) {
        this.position = position;
        this.collision = polygon;
    }

    public Vector2 getPosition() {
        return this.position;
    }

    public Polygon getCollision() {
        //this.collision.setTransform(box2d.coordPixelsToWorld(x,y),0);
        this.collision.setPosition(0,0);
        this.collision.translate(position.x, position.y);
        return this.collision;
    }

    public void destroy() {}

    @Override
    public int compareTo(Object o) {
        return (int)position.x;
    }
}
