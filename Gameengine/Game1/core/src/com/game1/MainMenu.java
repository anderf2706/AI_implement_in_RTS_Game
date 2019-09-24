package com.game1;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

import warp.WarpController;

public class MainMenu extends ApplicationAdapter implements Screen{
	
	final Game1 game;
	OrthographicCamera camera;
	MainMenuHud hud;
	
	public MainMenu(final Game1 game) {
		this.game = game;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1900, 1080);
		
		hud = new MainMenuHud(game.batch, game, this);
		
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(MainMenuHud.stage);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		
		game.batch.begin();
		
		game.batch.end();
		// TODO Auto-generated method stub
		hud.getStage().act(delta); //act the Hud
	    hud.getStage().draw(); //draw the Hud
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public void dispose() {
	}
	
	
	

}
