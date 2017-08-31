package com.av.game.graphics;

import com.av.game.gui.UI;
import com.av.game.logic.Game;
import com.av.game.logic.object.GameObject;
import com.av.game.logic.object.ObjectObserver;
import com.av.game.logic.object.building.Building;
import com.av.game.logic.object.helicopter.Helicopter;
import com.av.game.logic.object.item.IncreaseCapacity;
import com.av.game.logic.object.item.IncreaseVelocity;
import com.av.game.logic.object.item.Refuel;
import com.av.game.screen.util.ScreenInfo;
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
    private ShapeRenderer shape_renderer;
    private OrthographicCamera cam;

    private final Texture helicopter_texture;
    private final Texture refuel_texture;
    private final Texture building_texture;
    private final Texture item_texture;
    private final Texture cloud_texture;

    private LinkedList<Renderable> z0;
    private LinkedList<Renderable> z1;
    private LinkedList<BuildingSprite> buildings;
    private LinkedList<Renderable> z2;
    private LinkedList<Renderable> rmz0;
    private LinkedList<Renderable> rmz1;
    private LinkedList<BuildingSprite> rmbuildings;
    private LinkedList<Renderable> rmz2;

    private UI ui;

    private VisualObjectSpawner spawner;
    private float stateTime = 0f;

    private GameRenderer() {
        helicopter_texture = new Texture(Gdx.files.internal("helicopter.png"));
        refuel_texture = new Texture(Gdx.files.internal("gasoline.png"));
        building_texture = new Texture(Gdx.files.internal("building.png"));
        item_texture = new Texture(Gdx.files.internal("item.png"));
        cloud_texture = new Texture(Gdx.files.internal("cloud.png"));

        shape_renderer = new ShapeRenderer();
        batch = new SpriteBatch();
        cam = new OrthographicCamera(ScreenInfo.width, ScreenInfo.height);
        cam.position.set(ScreenInfo.width / 2f, ScreenInfo.height / 2f, 0);
        cam.update();

        z0 = new LinkedList<Renderable>();
        z1 = new LinkedList<Renderable>();
        buildings = new LinkedList<BuildingSprite>();
        z2 = new LinkedList<Renderable>();
        rmz0 = new LinkedList<Renderable>();
        rmz1 = new LinkedList<Renderable>();
        rmbuildings = new LinkedList<BuildingSprite>();
        rmz2 = new LinkedList<Renderable>();

        //ui = new GameUI(Game.getGame());
        ui = null;

        spawner = new VisualObjectSpawner(this);
    }

    public static void createInstance() {
        instance = new GameRenderer();
    }

    public static GameRenderer getInstance() {
        return instance;
    }

    public static void clear() {
        instance.z0.clear();
        instance.z1.clear();
        instance.buildings.clear();
        instance.z2.clear();
        instance.rmz0.clear();
        instance.rmz1.clear();
        instance.rmbuildings.clear();
        instance.rmz2.clear();
    }


    public SpriteBatch getBatch() {
        return batch;
    }

    public UI getUI() {
        return this.ui;
    }

    public void setUI(UI ui) {
        this.ui = ui;
    }


    void addZ0(Renderable renderable) {
        instance.z0.add(renderable);
    }

    void addZ1(Renderable renderable) {
        instance.z1.add(renderable);
    }

    void addBuildings(BuildingSprite renderable) {
        instance.buildings.add(renderable);
    }

    void addZ2(Renderable renderable) {
        instance.z2.add(renderable);
    }

    void rmZ0(Renderable renderable) {
        instance.rmz0.remove(renderable);
    }

    void rmBuildings(BuildingSprite renderable) {
        instance.rmbuildings.add(renderable);
    }

    void rmZ1(Renderable renderable) {
        instance.rmz1.add(renderable);
    }

    void rmZ2(Renderable renderable) {
        instance.rmz2.add(renderable);
    }


    static Vector3 getCamPosition() {
        return instance.cam.position;
    }

    static Texture getCloudTexture() {
        return instance.cloud_texture;
    }

    public void render(float delta_time) {
        Gdx.gl.glClearColor(0.2f,0.6f,0.9f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glDisable(GL20.GL_BLEND);

        stateTime += delta_time;

        spawner.update();
        removeRenderables();

        cam.position.set(cam.viewportWidth / 2f + Game.getGame().getHelicopter().getPosition().x - 200f,
                cam.viewportHeight / 2f, 0);
        cam.update();
        batch.setProjectionMatrix(cam.combined);
        shape_renderer.setProjectionMatrix(cam.combined);

        spriteRender(stateTime);
        //shapeRender();
    }

    private void removeRenderables() {
        for(Renderable rm : instance.rmz0)
            z0.remove(rm);
        for(Renderable rm : instance.rmz1)
            z1.remove(rm);
        for(BuildingSprite rm : instance.rmbuildings)
            buildings.remove(rm);
        for(Renderable rm : instance.rmz2)
            z2.remove(rm);
    }

    private void spriteRender(float state_time) {
        batch.begin();
        for(Renderable renderable : instance.z0)
            renderable.render(state_time, batch);
        for(BuildingSprite renderable : instance.buildings)
            renderable.render(state_time, batch);
        for(Renderable renderable : instance.z1)
            renderable.render(state_time, batch);
        for(Renderable renderable : instance.z2)
            renderable.render(state_time, batch);
        if(ui != null)
            ui.render(batch);
        batch.end();

    }

    private void shapeRender() {
        shape_renderer.setColor(Color.PURPLE);
        shape_renderer.begin(ShapeRenderer.ShapeType.Line);
        for(BuildingSprite renderable : instance.buildings)
            renderable.shapeRender(shape_renderer);
        for(Renderable renderable : instance.z1)
            renderable.shapeRender(shape_renderer);
        shape_renderer.end();
    }

    @Override
    public void objectCreated(GameObject object_created) {
        if(object_created.getClass().getSimpleName().equals(Refuel.class.getSimpleName()))
            addZ1(new ObjectSprite(object_created, refuel_texture));
        else if(object_created.getClass().getSimpleName().equals(Building.class.getSimpleName()))
            addBuildings(new BuildingSprite((Building) object_created, building_texture));
        else if(object_created.getClass().getSimpleName().equals(IncreaseCapacity.class.getSimpleName()))
            addZ1(new ObjectSprite(object_created, item_texture));
        else if(object_created.getClass().getSimpleName().equals(IncreaseVelocity.class.getSimpleName()))
            addZ1(new ObjectSprite(object_created, item_texture));
        else if(object_created.getClass().getSimpleName().equals(Helicopter.class.getSimpleName()))
            addZ1(new ObjectAnimation(object_created, helicopter_texture, 2, 4, 0.04f));
    }

    @Override
    public void objectDestroyed(GameObject object_destroyed) {
        for(Renderable renderable : z1) {
            if(((ObjectRender)renderable).getGameObject() == object_destroyed) {
                if(object_destroyed.getClass().getSimpleName().equals(Building.class.getSimpleName()))
                    rmBuildings((BuildingSprite) renderable);
                else
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
