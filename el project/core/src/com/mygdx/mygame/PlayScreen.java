package com.mygdx.mygame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class PlayScreen implements Screen {

    private static final float GRAVITY_X = 0.00f;
    private static final float GRAVITY_Y = -10.00f;

    private OrthographicCamera gameCam;
    private Viewport gamePort;

    private HUD hud;

    //tiled map variables
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;

    //box2d variables
    public static World world;
    private Box2DDebugRenderer debugRenderer;

    private MainCharacter mainCharacter;

    public PlayScreen()
    {
        gameCam = new OrthographicCamera(MyGame.V_WIDTH, MyGame.V_HEIGHT); // Sets camera to 256x224 world units
        gameCam.position.set(gameCam.viewportWidth / 2.0f, gameCam.viewportHeight / 2.0f, 0); // Sets (0,0) to bottom left

        gamePort = new FitViewport(gameCam.viewportWidth, gameCam.viewportHeight, gameCam); // viewport

        hud = new HUD(MyGame.batch);

        map = new TmxMapLoader().load("level1.tmx"); // load map
        mapRenderer = new OrthogonalTiledMapRenderer(map); // render map

        world = new World(new Vector2(GRAVITY_X, GRAVITY_Y), true); // define world
        debugRenderer = new Box2DDebugRenderer(); // for debugging purposes

        mainCharacter = MainCharacter.GetMainCharacter();
        mainCharacter.sprite = new Sprite(new Texture("Sprite-0001.png"));

        B2WorldCreator b2WorldCreator = new B2WorldCreator(map);
    }

    @Override
    public void show() {

    }

    public void handleInput(float dt)
    {
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP))
        {
            mainCharacter.b2body.applyLinearImpulse(new Vector2(0, 4f), mainCharacter.b2body.getWorldCenter(), true);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && mainCharacter.b2body.getLinearVelocity().x <= 20)
        {
            mainCharacter.b2body.applyLinearImpulse(new Vector2(10f, 0), mainCharacter.b2body.getWorldCenter(), true);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && mainCharacter.b2body.getLinearVelocity().x >= -20)
        {
            mainCharacter.b2body.applyLinearImpulse(new Vector2(-10f, 0), mainCharacter.b2body.getWorldCenter(), true);
        }
    }

    public void update (float dt){
        handleInput(dt);

        gameCam.position.set(mainCharacter.b2body.getPosition().x, gameCam.viewportHeight / 2, 0);

        gameCam.update();

        world.step(1/60f, 6, 2);

        mapRenderer.setView(gameCam);

        mainCharacter.sprite.setPosition((mainCharacter.b2body.getPosition().x) - (mainCharacter.sprite.getWidth() / 2.0f), (mainCharacter.b2body.getPosition().y) - (mainCharacter.sprite.getHeight() / 2.0f));
    }


    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mapRenderer.render();
        debugRenderer.render(world, gameCam.combined);

        hud.stage.act();
        hud.stage.draw();

        MyGame.batch.setProjectionMatrix(gameCam.combined);

        MyGame.batch.begin();
        mainCharacter.sprite.draw(MyGame.batch);
        MyGame.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        map.dispose();
        mapRenderer.dispose();
        world.dispose();
        debugRenderer.dispose();
        hud.dispose();
    }
}
