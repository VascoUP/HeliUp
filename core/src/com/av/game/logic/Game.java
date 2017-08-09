package com.av.game.logic;

import com.av.game.logic.object.GameObject;
import com.av.game.logic.object.item.Item;
import com.av.game.logic.object.helicopter.Helicopter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import java.util.LinkedList;

public class Game {
    private static Game instance;

    private Helicopter helicopter;
    private LinkedList<Item> items;
    private LinkedList<GameObject> buildings;

    private Game() {
        items = new LinkedList<Item>();
        buildings = new LinkedList<GameObject>();
    }

    public static void createInstance() {
        instance = new Game();
    }

    public static Game getGame() {
        return instance;
    }

    public void create() {
        helicopter = new Helicopter(new Vector2(200f, 200f));
        items.clear();
        buildings.clear();
    }

    public Helicopter getHelicopter() {
        return helicopter;
    }

    public boolean endGame() {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        return (helicopter.getPosition().y < 50f || helicopter.getPosition().y > 800 * (h / w));
    }
}
