package com.av.game.graphics;

import com.av.game.HeliGame;
import com.av.game.gui.GameUI;
import com.av.game.logic.Game;
import com.av.game.logic.object.GameObject;
import com.av.game.logic.object.ObjectObserver;
import com.av.game.logic.object.ObjectsNotifier;
import com.av.game.logic.object.building.Building;
import com.av.game.logic.object.helicopter.Helicopter;
import com.av.game.logic.object.item.IncreaseCapacity;
import com.av.game.logic.object.item.Refuel;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

import java.util.LinkedList;

public class GameRenderer implements ObjectObserver {
    private static GameRenderer instance;

    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private OrthographicCamera cam;

    private final Texture helicopter_texture;
    private final Texture refuel_texture;
    private final Texture building_texture;
    private final Texture item_texture;
    private final Texture cloud_texture;

    private LinkedList<Renderable> z0;
    private LinkedList<Renderable> z1;
    private LinkedList<Renderable> z2;
    private LinkedList<Renderable> rmz0;
    private LinkedList<Renderable> rmz1;
    private LinkedList<Renderable> rmz2;

    private GameUI ui;

    private VisualObjectSpawner spawner;
    private float stateTime = 0f;

    private GameRenderer() {
        helicopter_texture = new Texture(Gdx.files.internal("helicopter.png"));
        refuel_texture = new Texture(Gdx.files.internal("gasoline.png"));
        building_texture = new Texture(Gdx.files.internal("building.png"));
        item_texture = new Texture(Gdx.files.internal("item.png"));
        cloud_texture = new Texture(Gdx.files.internal("cloud.png"));

        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();
        cam = new OrthographicCamera(HeliGame.VIEW_WIDTH, HeliGame.VIEW_HEIGHT);
        cam.position.set(HeliGame.VIEW_WIDTH / 2f, HeliGame.VIEW_HEIGHT / 2f, 0);
        cam.update();

        z0 = new LinkedList<Renderable>();
        z1 = new LinkedList<Renderable>();
        z2 = new LinkedList<Renderable>();
        rmz0 = new LinkedList<Renderable>();
        rmz1 = new LinkedList<Renderable>();
        rmz2 = new LinkedList<Renderable>();
        ui = new GameUI(Game.getGame());
        ObjectsNotifier.addObserver(this);
        spawner = new VisualObjectSpawner(this);
    }

    public static void createInstance() {
        instance = new GameRenderer();
    }

    public static GameRenderer getInstance() {
        return instance;
    }

    public static void clear() {
        instance.z1.clear();
    }

    public void addZ0(Renderable renderable) {
        instance.z0.add(renderable);
    }

    public void addZ1(Renderable renderable) {
        instance.z1.add(renderable);
    }

    public void addZ2(Renderable renderable) {
        instance.z2.add(renderable);
    }

    public void rmZ0(Renderable renderable) {
        instance.rmz0.remove(renderable);
    }

    public void rmZ1(Renderable renderable) {
        instance.rmz1.add(renderable);
    }

    public void rmZ2(Renderable renderable) {
        instance.rmz2.add(renderable);
    }


    public static Vector3 getCamPosition() {
        return instance.cam.position;
    }

    public static Texture getCloudTexture() {
        return instance.cloud_texture;
    }

    public void render() {
        Gdx.gl.glClearColor(0.2f,0.6f,0.9f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glDisable(GL20.GL_BLEND);

        stateTime += Gdx.graphics.getDeltaTime();

        spawner.update();
        removeRenderables();

        cam.position.set(cam.viewportWidth / 2f + Game.getGame().getHelicopter().getPosition().x - 200f,
                cam.viewportHeight / 2f, 0);
        cam.update();
        batch.setProjectionMatrix(cam.combined);
        shapeRenderer.setProjectionMatrix(cam.combined);

        spriteRender(stateTime);
        //shapeRender();
    }

    private void removeRenderables() {
        for(Renderable rm : instance.rmz0)
            z0.remove(rm);
        for(Renderable rm : instance.rmz1)
            z1.remove(rm);
        for(Renderable rm : instance.rmz2)
            z2.remove(rm);
    }

    private void spriteRender(float stateTime) {
        batch.begin();
        for(Renderable renderable : instance.z0)
            renderable.render(stateTime, batch);
        for(Renderable renderable : instance.z1)
            renderable.render(stateTime, batch);
        for(Renderable renderable : instance.z2)
            renderable.render(stateTime, batch);
        ui.render(batch);
        batch.end();

    }

    private void shapeRender() {
        shapeRenderer.setColor(Color.PURPLE);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        for(Renderable renderable : instance.z1)
            renderable.shapeRender(shapeRenderer);
        shapeRenderer.end();
    }

    @Override
    public void objectCreated(GameObject objectCreated) {
        if(objectCreated.getClass().getSimpleName().equals(Refuel.class.getSimpleName()))
            addZ1(new ObjectSprite(objectCreated, refuel_texture));
        else if(objectCreated.getClass().getSimpleName().equals(Building.class.getSimpleName()))
            addZ1(new ObjectSprite(objectCreated, building_texture));
        else if(objectCreated.getClass().getSimpleName().equals(IncreaseCapacity.class.getSimpleName()))
            addZ1(new ObjectSprite(objectCreated, item_texture));
        else if(objectCreated.getClass().getSimpleName().equals(Helicopter.class.getSimpleName()))
            addZ1(new ObjectAnimation(objectCreated, helicopter_texture, 2, 4, 0.04f));
    }

    @Override
    public void objectDestroyed(GameObject objectDestroyed) {
        for(Renderable renderable : z1) {
            if(((ObjectRender)renderable).getGameObject() == objectDestroyed) {
                rmZ1(renderable);
                break;
            }
        }
    }

    public static void dispose() {
        instance.batch.dispose();
        instance.helicopter_texture.dispose();
        instance.refuel_texture.dispose();
        instance.building_texture.dispose();
    }
}
