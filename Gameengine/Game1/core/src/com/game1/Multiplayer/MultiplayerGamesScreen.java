package com.game1.Multiplayer;

import com.game1.Game1;
import com.game1.GameScreen;

import warp.WarpListener;

public class MultiplayerGamesScreen extends GameScreen implements WarpListener{

	public MultiplayerGamesScreen(Game1 game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onWaitingStarted(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onError(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGameStarted(String message) {
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

}
