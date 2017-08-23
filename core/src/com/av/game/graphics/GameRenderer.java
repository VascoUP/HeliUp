package com.av.game.graphics;

import com.av.game.logic.object.GameObject;
import com.av.game.logic.object.ObjectObserver;
import com.av.game.logic.object.ObjectsNotifier;
import com.av.game.logic.object.building.Building;
import com.av.game.logic.object.helicopter.Helicopter;
import com.av.game.logic.object.item.Refuel;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.LinkedList;

public class GameRenderer implements ObjectObserver {
    private static GameRenderer instance;

    private final Texture helicopter_texture;
    private final Texture refuel_texture;
    private final Texture building_texture;

    private LinkedList<Renderable> renderables;

    private GameRenderer() {
        helicopter_texture = new Texture(Gdx.files.internal("helicopter.png"));
        refuel_texture = new Texture(Gdx.files.internal("gasoline.png"));
        building_texture = new Texture(Gdx.files.internal("building.png"));
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
            addRenderable(new ObjectSprite(objectCreated, refuel_texture));
        else if(objectCreated.getClass().getSimpleName().equals(Building.class.getSimpleName()))
            addRenderable(new ObjectSprite(objectCreated, building_texture));
        else if(objectCreated.getClass().getSimpleName().equals(Helicopter.class.getSimpleName()))
            addRenderable(new ObjectAnimation(objectCreated, helicopter_texture, 2, 4, 0.04f));
    }

    @Override
    public void objectDestroyed(GameObject objectDestroyed) {
        for(Renderable renderable : renderables) {
            if(((ObjectRender)renderable).getGameObject() == objectDestroyed) {
                rmRenderable(renderable);
                break;
            }
        }
    }

    public static void dispose() {
        instance.helicopter_texture.dispose();
        instance.refuel_texture.dispose();
        instance.building_texture.dispose();
    }
}
