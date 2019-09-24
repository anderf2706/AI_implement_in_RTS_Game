package com.game1;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class StartScreen extends ApplicationAdapter implements Screen{

	final Game1 game;
	OrthographicCamera camera;
	
	public StartScreen(final Game1 game) {
		this.game = game;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1900, 1080);
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.5f, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		
		game.batch.begin();
		game.batch.draw(game.frontimg, 0, 0);
		game.font.draw(game.batch, "Durs Game", 1900/2, 1080/2 + 300, 0, 9, 200, 6, false);
		game.font.draw(game.batch, "Press to continue", 1900/2 - 250, 1080/2 + 100);
		game.batch.end();
		//game.setScreen(new GameScreen(game));
		
		if(Gdx.input.isTouched()) {
			game.setScreen(new MainMenu(game));
			this.dispose();
		}
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		
	}
	
	
	
	

}
