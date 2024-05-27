package com.mygdx.mygame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class PlayScreen implements Screen {
    private MyGame game;
    private OrthographicCamera gamecam;
    private HUD hud;
    private Viewport gamePort;
    Texture  mario;

    public PlayScreen(MyGame game){
        this.game=game;
        gamecam = new OrthographicCamera();
        gamePort= new FitViewport(MyGame.V_WIDTH,MyGame.V_HEIGHT,gamecam);
        hud = new HUD(game.batch);
        mario = new Texture("mario_ntsc.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

        game.batch.begin();
        game.batch.draw(mario,0,0);
        game.batch.end();
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
