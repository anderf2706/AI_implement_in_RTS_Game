package com.game1;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class Building implements InputProcessor, Screen{
	
	Rectangle the_building;
	GameScreen gamescreen;
	Texture building;
	Texture green;
	
	boolean buildingChosen = false;
	
	BitmapFont font = new BitmapFont();
	
	ArrayList<Node> BNlist = new ArrayList<Node>();

	
	public Building(GameScreen gamescreen, int x, int y, int width, int height) {
		this.gamescreen = gamescreen;
		the_building = new Rectangle();
		the_building.height = height;
		the_building.width = width;
		the_building.x = x - the_building.width / 2;
		the_building.y= y - the_building.height / 2;

		building = new Texture(Gdx.files.internal("bucket.png"));
		green = new Texture(Gdx.files.internal("green.jpg"));
		
		
		this.gamescreen.buildings.add(this);
		
		setBN();
		
		
	}
	
	
	
	public void setBN() {
		for (Node node : gamescreen.allnodes) {
			
		    if (Intersector.overlaps(node.body, the_building)) {
		    	node.BN = true;
		    	BNlist.add(node);
		    	
		    
		    }
		    
		}
		for (Node node : BNlist) {
	    	for (Node innernode : node.adjecent) {
				if(innernode != null) {
					if(!innernode.BN) {
						innernode.ACNode = true;
					}
				}
			}
			
		}
		
	}
	
	public void overlap(int button) {
		if((Intersector.overlaps(gamescreen.the_mouse, the_building))){
			buildingChosen = true;
			System.out.println("player_chosen");
		
		}
		if(!(Intersector.overlaps(gamescreen.the_mouse, the_building)) && (button == Input.Buttons.LEFT)) {
			buildingChosen = false;
			System.out.println("unchosen");
		}
	}
	
	public void destroyed() {
		gamescreen.makenodes(gamescreen.mapobjects, gamescreen.objects);
			
	}
	
	public void batch(SpriteBatch batch) {
		
		batch.draw(building, the_building.x, the_building.y, the_building.width, the_building.height);
		
		if(buildingChosen) {
			batch.draw(green, the_building.x, the_building.y + 75, 50, 10);
		}
		
		
	}



	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
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



	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		this.overlap(button);
		return false;
	}



	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}



}