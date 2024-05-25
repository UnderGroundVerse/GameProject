package com.mygdx.mygame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Game;

public class MyGame extends Game {
	public SpriteBatch batch;

	@Override
	public void create() {
		batch = new SpriteBatch();
		setScreen(new PlayScreen(this));


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
