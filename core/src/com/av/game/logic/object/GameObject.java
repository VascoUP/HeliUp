package com.av.game.logic.object;

import java.awt.geom.Point2D;

public class GameObject {
    private Point2D position;

    public GameObject (Point2D position) {
        this.position = position;
    }

    public Point2D getPosition() {
        return this.position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }
}
