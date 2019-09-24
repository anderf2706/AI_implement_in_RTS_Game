package com.game1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Hud extends InputAdapter implements Screen{

	public Stage stage;
	public FitViewport stageViewport;
	 Texture texture;
     Drawable drawable;
     Drawable drawablebackground;
     Texture background;
     GameScreen gamescreen;
     Table table;

	public Hud(SpriteBatch spriteBatch, final GameScreen gamescreen) {
		stageViewport = new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
	    stage = new Stage(stageViewport, spriteBatch); //create stage with the stageViewport and the SpriteBatch given in Constructor
	    texture = new Texture(Gdx.files.internal("Fighter-Front.gif"));
	    background = new Texture(Gdx.files.internal("brown rectangle.jfif"));
	    drawablebackground = new TextureRegionDrawable((new TextureRegion(background)));
	    drawable = new TextureRegionDrawable((new TextureRegion(texture)));
	    this.gamescreen = gamescreen;

	    table = new Table();
	    table.setVisible(true);
	    
	   
       ImageButton imageButton = new ImageButton(drawable);
       imageButton.setPosition(200, Gdx.graphics.getHeight()-50);
       imageButton.addListener( new ClickListener() {              
    	    @Override
    	    public void clicked(InputEvent event, float x, float y) {
    	    	gamescreen.makeHouse = false;
    	    	gamescreen.makeWall = false;
    	    	gamescreen.makeCastle = false;
    	       gamescreen.makeBarracks = !gamescreen.makeBarracks;
     	    };
    	});
       
       ImageButton imageButton2 = new ImageButton(drawable);
       imageButton2.setPosition(300, Gdx.graphics.getHeight()-50);
       imageButton2.addListener( new ClickListener() {              
    	    @Override
    	    public void clicked(InputEvent event, float x, float y) {
    	    	gamescreen.makeBarracks = false;
    	    	gamescreen.makeWall = false;
    	    	gamescreen.makeCastle = false;
    	        gamescreen.makeHouse = !gamescreen.makeHouse;
    	    };
    	});
       
       ImageButton imageButton3 = new ImageButton(drawable);
       imageButton3.setPosition(400, Gdx.graphics.getHeight()-50);
       imageButton3.addListener( new ClickListener() {              
    	    @Override
    	    public void clicked(InputEvent event, float x, float y) {
    	    	gamescreen.makeHouse = false;
    	    	gamescreen.makeBarracks = false;
    	    	gamescreen.makeCastle = false;
    	        gamescreen.makeWall = !gamescreen.makeWall;
    	    };
    	});
       
       ImageButton imageButton4 = new ImageButton(drawable);
       imageButton4.setPosition(500, Gdx.graphics.getHeight()-50);
       imageButton4.addListener( new ClickListener() {              
    	    @Override
    	    public void clicked(InputEvent event, float x, float y) {
    	    	gamescreen.makeHouse = false;
    	    	gamescreen.makeWall = false;
    	    	gamescreen.makeBarracks = false;
    	    	gamescreen.makeCastle = !gamescreen.makeCastle;
    	    };
    	});
       
       ImageButton imageButton5 = new ImageButton(drawable);
       imageButton5.setPosition(600, Gdx.graphics.getHeight()-50);
       imageButton5.addListener( new ClickListener() {              
    	    @Override
    	    public void clicked(InputEvent event, float x, float y) {
    	        System.out.println("dd");
    	    };
    	});
       
       Image background = new Image(drawablebackground);
       background.setPosition(0, Gdx.graphics.getHeight() - 150);
       background.setWidth(Gdx.graphics.getWidth());
       background.setHeight(150);
       background.toBack();
       
       
              	
        //stage.addActor(imageButton);
        table.addActor(imageButton);
        table.addActor(imageButton2);
        table.addActor(imageButton3);
        table.addActor(imageButton4);
        table.addActor(imageButton5);
        stage.addActor(background);

        stage.addActor(table);
        //stage.addActor(imageButton2);
	}

	public Stage getStage() { return stage; }

	public void dispose(){
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		//Gdx.input.setInputProcessor(this);


		
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
}