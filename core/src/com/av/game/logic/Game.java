package com.av.game.logic;

import com.av.game.HeliGame;
import com.av.game.logic.object.GameObject;
import com.av.game.logic.object.helicopter.Helicopter;
import com.av.game.logic.object.item.Item;
import com.av.game.logic.physics.CollisionObserver;
import com.av.game.logic.physics.Physics;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import java.util.LinkedList;

public class Game {
    private static Game instance;

    private boolean endGame;

    private Helicopter helicopter;
    private LinkedList<Item> items;
    private LinkedList<GameObject> buildings;

    private Game() {
        Physics.createInstance();
        CollisionObserver.createInstance();
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
        Physics.clear();
        CollisionObserver.clear();

        endGame = false;
        items.clear();
        buildings.clear();
        helicopter = new Helicopter(new Vector2(200f, HeliGame.VIEW_HEIGHT / 2f));
    }

    public Helicopter getHelicopter() {
        return helicopter;
    }


    public void update() {
        Physics.getInstance().update();
        CollisionObserver.getInstance().checkCollisions();
        if(endGame) return;
        helicopter.update();
    }

    public boolean isGameOver() {
        return endGame || (helicopter.getPosition().y < 0 || helicopter.getPosition().y > HeliGame.VIEW_HEIGHT);
    }

    public void endGame() {
        endGame = true;
    }
}
