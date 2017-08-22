package com.av.game.graphics;

import com.av.game.logic.Game;
import com.av.game.logic.object.GameObject;
import com.av.game.logic.object.ObjectObserver;
import com.av.game.logic.object.ObjectsNotifier;
import com.av.game.logic.object.building.Building;
import com.av.game.logic.object.item.Item;
import com.av.game.logic.object.item.Refuel;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.LinkedList;

public class GameRenderer implements ObjectObserver {
    private static GameRenderer instance;

    private LinkedList<Renderable> renderables;

    private GameRenderer() {
        renderables = new LinkedList<Renderable>();
        ObjectsNotifier.addObserver(this);
    }

    public static void createInstance() {
        instance = new GameRenderer();
    }

    public static GameRenderer getInstance() {
        return instance;
    }

    public static void clear() {
        instance.renderables.clear();
    }

    public static void addRenderable(Renderable renderable) {
        instance.renderables.add(renderable);
    }

    public static void rmRenderable(Renderable renderable) {
        instance.renderables.remove(renderable);
    }

    public static void render(float stateTime, SpriteBatch batch) {
        for(Renderable renderable : instance.renderables)
            renderable.render(stateTime, batch);
    }

    public static void shapeRender(ShapeRenderer renderer) {
        for(Renderable renderable : instance.renderables)
            renderable.shapeRender(renderer);
    }

    @Override
    public void objectCreated(GameObject objectCreated) {
        Gdx.app.log(GameRenderer.class.getSimpleName(), "Object added");
        if(objectCreated.getClass().getSimpleName().equals(Refuel.class.getSimpleName()))
            addRenderable(new ObjectSprite(objectCreated, "gasoline.png"));
        else if(objectCreated.getClass().getSimpleName().equals(Building.class.getSimpleName()))
            addRenderable(new ObjectSprite(objectCreated, "building.png"));
    }

    @Override
    public void objectDestroyed(GameObject objectDestroyed) {
        for(Renderable renderable : renderables) {
            if(((ObjectRender)renderable).getGameObject() == objectDestroyed) {
                renderable.dispose();
                rmRenderable(renderable);
                break;
            }
        }
    }
}
