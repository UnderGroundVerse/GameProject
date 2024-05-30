package com.mygdx.mygame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class PlayScreen implements Screen {
    private MyGame game;

    private OrthographicCamera gamecam;
    private Viewport gamePort;

    private HUD hud;

    //tiled map variables
    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;

    //box2d variable
    public static World world;
    private Box2DDebugRenderer b2dr;

    private MainCharacter mainCharacter;

    public PlayScreen(MyGame game){
        this.game=game;
    }

    @Override
    public void show() {
        gamecam = new OrthographicCamera();
        gamePort= new FitViewport(MyGame.V_WIDTH,MyGame.V_HEIGHT,gamecam);
        hud = new HUD(game.batch);

        //map
        maploader = new TmxMapLoader();
        map = maploader.load("map.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map);
        gamecam.position.set(gamePort.getWorldWidth() /2 ,gamePort.getWorldHeight()/2 ,0);

        //box2d
        world = new World(new Vector2(0,-9.81f*32),true);
        b2dr = new Box2DDebugRenderer();

        new B2WorldCreator(map);

        mainCharacter = MainCharacter.GetMainCharacter();
    }


    public void handleInput(float dt){
        if(Gdx.input.isKeyJustPressed(Input.Keys.W))
            mainCharacter.b2body.applyLinearImpulse(new Vector2(0,500f), mainCharacter.b2body.getWorldCenter(),true );

        if(Gdx.input.isKeyPressed(Input.Keys.D) && mainCharacter.b2body.getLinearVelocity().x <= 100)
            mainCharacter.b2body.applyLinearImpulse(new Vector2(10,0), mainCharacter.b2body.getWorldCenter(),true);

        if(Gdx.input.isKeyPressed(Input.Keys.A) && mainCharacter.b2body.getLinearVelocity().x >= -100)
            mainCharacter.b2body.applyLinearImpulse(new Vector2(-10,0),mainCharacter.b2body.getWorldCenter(),true);


    }
    public void update (float dt){

        handleInput(dt);
        gamecam.position.x = mainCharacter.b2body.getPosition().x;
        gamecam.update();
        mapRenderer.setView(gamecam);
        world.step(1/60f,8,5);


    }


    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

        mapRenderer.render();

        b2dr.render(world,gamecam.combined);
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
        b2dr.dispose();
        hud.dispose();
    }
}
