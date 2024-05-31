package com.mygdx.mygame;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Game;

public class MyGame extends Game {
	public static SpriteBatch batch;
	public static final int V_WIDTH = 256, V_HEIGHT = 224;

	@Override
	public void create() {
		batch = new SpriteBatch();
		setScreen(new PlayScreen());
	}

	@Override
	public void render() {
		super.render();

	}

	@Override
	public void resize(int width ,int height){
		super.resize(width,height);

	}
	
	@Override
	public void dispose() {
		super.dispose();

	}

	@Override
	public void pause(){
		super.pause();
	}
	@Override
	public void resume(){
		super.resume();
	}
}
