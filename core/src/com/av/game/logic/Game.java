package com.av.game.logic;

import com.av.game.HeliGame;
import com.av.game.logic.object.CollidableObject;
import com.av.game.logic.object.GameObject;
import com.av.game.logic.object.ObjectsNotifier;
import com.av.game.logic.object.helicopter.Helicopter;
import com.av.game.logic.object.item.Item;
import com.av.game.logic.physics.CollisionObserver;
import com.av.game.logic.physics.Physics;
import com.badlogic.gdx.math.Vector2;

import java.util.LinkedList;

public class Game {
    private static Game instance;

    private boolean endGame;

    private Helicopter helicopter;
    private LinkedList<Item> items;
    private LinkedList<GameObject> buildings;

    private ObjectSpawn objectSpawn;

    private Game() {
        Physics.createInstance();
        CollisionObserver.createInstance();
        ObjectsNotifier.createInstance();
        items = new LinkedList<Item>();
        buildings = new LinkedList<GameObject>();
        objectSpawn = new ObjectSpawn(this);
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
        ObjectsNotifier.createInstance();
        items.clear();
        buildings.clear();
        objectSpawn = new ObjectSpawn(this);

        endGame = false;
        helicopter = new Helicopter(new Vector2(200f, HeliGame.VIEW_HEIGHT / 2f));
    }

    public Helicopter getHelicopter() {
        return helicopter;
    }

    public void addItem(Item item) {
        items.add(item);
        addObject((GameObject)item);
    }

    public void rmItem(Item item) {
        items.remove(item);
        rmObject((GameObject)item);
    }

    public void addBuilding(GameObject building) {
        buildings.add(building);
        addObject(building);
    }

    public void rmBuilding(GameObject building) {
        buildings.remove(building);
        rmObject(building);
    }

    private void addObject(GameObject object) {
        ObjectsNotifier.getInstance().notifyCreate(object);
    }

    public void rmCollidable(CollidableObject collidable) {
        if(Item.class.isInstance(collidable))
            rmItem((Item)collidable);
        else
            rmObject(collidable);
    }

    private void rmObject(GameObject object) {
        ObjectsNotifier.getInstance().notifyDestroy(object);
    }

    public void update(float deltaTime) {
        Physics.getInstance().update();
        CollisionObserver.getInstance().checkCollisions();
        if(endGame) return;
        objectSpawn.update(deltaTime);
        helicopter.update();
    }

    public boolean isGameOver() {
        return endGame || (helicopter.getPosition().y < 0 || helicopter.getPosition().y > HeliGame.VIEW_HEIGHT);
    }

    public void endGame() {
        endGame = true;
    }

    public boolean objectOutOfBounds(GameObject object) {
        return object.getPosition().x + object.getCollision().getBoundingRectangle().getWidth() < helicopter.getPosition().x - 200f;
    }
}
