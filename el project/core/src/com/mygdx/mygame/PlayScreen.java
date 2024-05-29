package com.mygdx.mygame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class PlayScreen implements Screen {
    private MyGame game;
    private OrthographicCamera gamecam;
    private HUD hud;
    private Viewport gamePort;
    Texture  mario;

    //tiled map variables
    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;

    //box2d variable
    private World world;
    private Box2DDebugRenderer b2dr;

    private MC mc;

    public PlayScreen(MyGame game){
        this.game=game;

    }



    @Override
    public void show() {
        gamecam = new OrthographicCamera();
        gamePort= new FitViewport(MyGame.V_WIDTH,MyGame.V_HEIGHT,gamecam);
        hud = new HUD(game.batch);
        mario = new Texture("mario_ntsc.png");

        //map
        maploader = new TmxMapLoader();
        map = maploader.load("map1.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map);
        gamecam.position.set(gamePort.getWorldWidth() /2 ,gamePort.getWorldHeight()/2 ,0);

        //box2d
        world = new World(new Vector2(0,-9.81f*32),true);
        b2dr = new Box2DDebugRenderer();

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        for(MapObject object : map.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect =  ( (RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set(rect.getX()+rect.getWidth()/2 , rect.getY()+rect.getHeight()/2);
            body =world.createBody(bdef);

            shape.setAsBox(rect.getWidth()/2, rect.getHeight()/2);

            fdef.shape = shape;

            body.createFixture(fdef);

        }
       mc = new MC(world);

    }


    public void handleInput(float dt){
        if(Gdx.input.isKeyJustPressed(Input.Keys.W))
            mc.b2body.applyLinearImpulse(new Vector2(0,500f),mc.b2body.getWorldCenter(),true );

        if(Gdx.input.isKeyPressed(Input.Keys.D) && mc.b2body.getLinearVelocity().x <= 100)
            mc.b2body.applyLinearImpulse(new Vector2(10,0),mc.b2body.getWorldCenter(),true);

        if(Gdx.input.isKeyPressed(Input.Keys.A) && mc.b2body.getLinearVelocity().x >= -100)
            mc.b2body.applyLinearImpulse(new Vector2(-10,0),mc.b2body.getWorldCenter(),true);


    }
    public void update (float dt){

        handleInput(dt);
        gamecam.position.x = mc.b2body.getPosition().x;
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
        game.batch.begin();
       // game.batch.draw(mario,0,0);
        game.batch.end();
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

    }
}
