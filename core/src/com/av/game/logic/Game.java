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
    private boolean retried;

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
        ObjectSpawn.reset();

        endGame = false;
        retried = false;

        //Initialize helicopter
        helicopter = new Helicopter(new Vector2(200f, ScreenInfo.height / 2f));
    }

    public boolean getRetried() {
        return retried;
    }

    public Helicopter getHelicopter() {
        return helicopter;
    }

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

    public void retry() {
        //Clear all classes of previous Games' objects
        Physics.clear();
        CollisionNotifier.clear();
        ObjectsNotifier.clear();

        //Initialize objectSpawn
        ObjectSpawn.reset();

        Physics.addObject(helicopter);
        CollisionNotifier.addCollisionObject(helicopter);

        endGame = false;
        retried = true;

        //Reset heli fuel
        helicopter.getHeliFuel().setFuel(Game.getGame().getHelicopter().getHeliFuel().getCapacity());
        helicopter.resetPositionY();
    }

    public boolean objectOutOfBounds(GameObject object) {
        //Check if object is HORIZONTALLY out of bounds (not used for helicopter)
        return object.getPosition().x + object.getCollision().getBoundingRectangle().getWidth() < helicopter.getPosition().x - 200f;
    }
}
