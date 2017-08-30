package com.av.game.logic;

import com.av.game.logic.object.GameObject;
import com.av.game.logic.object.ObjectsNotifier;
import com.av.game.logic.object.helicopter.Helicopter;
import com.av.game.logic.physics.CollisionNotifier;
import com.av.game.logic.physics.Physics;
import com.av.game.logic.spawn.ObjectSpawn;
import com.av.game.screen.util.ScreenInfo;
import com.badlogic.gdx.math.Vector2;

public class Game {
    //Singleton class: only one instance is allowed to exist
    private static Game instance;

    //Variable that tells if game is over or not
    private boolean endGame;

    //Helicopter instance
    private Helicopter helicopter;

    //private LinkedList<Item> items;
    //private LinkedList<GameObject> buildings;

    private Game() {
        //Create singleton instances of necessary classes
        Physics.createInstance();
        CollisionNotifier.createInstance();
        ObjectsNotifier.createInstance();
        ObjectSpawn.createInstance();

        //items = new LinkedList<Item>();
        //buildings = new LinkedList<GameObject>();
    }

    public static void createInstance() {
        instance = new Game();
    }

    public static Game getGame() {
        return instance;
    }

    public void create() {
        //Clear all classes of previous Games' objects
        Physics.clear();
        CollisionNotifier.clear();
        ObjectsNotifier.clear();

        //items.clear();
        //buildings.clear();

        //Initialize objectSpawn
        ObjectSpawn.reset();

        endGame = false;

        //Initialize helicopter
        helicopter = new Helicopter(new Vector2(200f, ScreenInfo.height / 2f));
    }

    public Helicopter getHelicopter() {
        return helicopter;
    }

    /*void addItem(Item item) {
        //items.add(item);
        addObject((GameObject)item);
    }

    public void rmItem(Item item) {
        //items.remove(item);
        rmObject((GameObject)item);
    }

    void addBuilding(GameObject building) {
        //buildings.add(building);
        addObject(building);
    }

    public void rmBuilding(GameObject building) {
        //buildings.remove(building);
        rmObject(building);
    }



    public void rmCollidable(CollidableObject collidable) {
        if(Item.class.isInstance(collidable))
            rmItem((Item)collidable);
        else
            rmObject(collidable);
    }*/

    public void addObject(GameObject object) {
        ObjectsNotifier.getInstance().notifyCreate(object);
    }

    public void rmObject(GameObject object) {
        ObjectsNotifier.getInstance().notifyDestroy(object);
    }

    public void update() {
        //Used to calculate delta distance (of helicopter)
        float original_x = helicopter.getPosition().x;

        //Update Physics and Collisions
        Physics.getInstance().update();
        CollisionNotifier.getInstance().checkCollisions();

        //Check end game
        if(endGame)
            return;

        //Update objectSpawn with delta distance
        ObjectSpawn.getInstance().update(helicopter.getPosition().x - original_x);

        //Update helicopter
        helicopter.update();
    }

    public boolean isGameOver() {
        return endGame ||
            //If helicopter goes below of above screen borders
            (helicopter.getPosition().y < 0 || helicopter.getPosition().y + helicopter.getCollision().getBoundingRectangle().getHeight() > ScreenInfo.height);
    }

    public void endGame() {
        endGame = true;
    }

    public boolean objectOutOfBounds(GameObject object) {
        //Check if object is HORIZONTALLY out of bounds (not used for helicopter)
        return object.getPosition().x + object.getCollision().getBoundingRectangle().getWidth() < helicopter.getPosition().x - 200f;
    }
}
