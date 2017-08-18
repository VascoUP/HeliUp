package com.av.game.graphics;

import com.av.game.logic.object.GameObject;
import com.av.game.logic.object.ObjectObserver;
import com.av.game.logic.object.ObjectsNotifier;
import com.av.game.logic.object.item.Item;
import com.av.game.logic.object.item.Refuel;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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

    @Override
    public void objectCreated(GameObject objectCreated) {
        boolean isItem = false;
        for(Class<?> c : objectCreated.getClass().getInterfaces()) {
            if(c.getSimpleName().equals(Item.class.getSimpleName())) {
                isItem = true;
                break;
            }
        }
        if(isItem) {
            if(objectCreated.getClass().getSimpleName().equals(Refuel.class.getSimpleName()))
                addRenderable(new ObjectSprite(objectCreated, "gasoline.png"));
        }
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
