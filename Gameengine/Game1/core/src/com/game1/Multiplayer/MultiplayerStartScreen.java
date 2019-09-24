package com.game1.Multiplayer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.game1.Game1;
import com.game1.MainMenuHud;

import warp.WarpController;
import warp.WarpListener;

public class MultiplayerStartScreen implements Screen, WarpListener{
	
	
	final Game1 game;
	OrthographicCamera camera;
	WarpController WC;
	
	
	private final String[] tryingToConnect = {"Connecting","to AppWarp"};
	private final String[] waitForOtherUser = {"Waiting for","other user"};
	private final String[] errorInConnection = {"Error in","Connection", "Go Back"};
	
	private final String[] game_win = {"Congrats You Win!", "Enemy Defeated"};
	private final String[] game_loose = {"Oops You Loose!","Target Achieved","By Enemy"};
	private final String[] enemy_left = {"Congrats You Win!", "Enemy Left the Game"};
	
	private String[] msg = tryingToConnect;
	
	public MultiplayerStartScreen(final Game1 game) {
		this.game = game;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1900, 1080);
		WarpController.getInstance().startApp("me2");

		WarpController.getInstance().setListener(this);

		
		
		
		

		
	}

	@Override
	public void onWaitingStarted(String message) {
		//this.msg = waitForOtherUser;
		// TODO Auto-generated method stub
		System.out.println(message);
		
	}

	@Override
	public void onError(String message) {
		// TODO Auto-generated method stub
		//this.msg = errorInConnection;
		
	}

	@Override
	public void onGameStarted(String message) {
		Gdx.app.postRunnable(new Runnable() {
			@Override
			public void run () {
				game.setScreen( new MultiplayerGamesScreen(game));
				
			}
		});
		this.dispose();
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGameFinished(int code, boolean isRemote) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGameUpdateReceived(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		
		game.batch.begin();
		
		game.batch.end();
		
		
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
		// TODO Auto-generated method stub
		
	}

}
