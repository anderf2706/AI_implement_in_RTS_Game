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
import com.game1.Multiplayer.MultiplayerStartScreen;
import com.shephertz.app42.gaming.multiplayer.client.WarpClient;

import warp.WarpController;

public class MainMenuHud extends InputAdapter implements Screen{
	
	
	public static Stage stage;
	public FitViewport stageViewport;
	 Texture texture;
     Drawable drawable;
     Drawable drawablebackground;
     Texture background;
     Game1 game;
     GameScreen gamescreen;
     MultiplayerStartScreen MPSS;
     Table table;
     MainMenu mainmenu;

	public MainMenuHud(SpriteBatch spriteBatch, final Game1 game, final MainMenu mainmenu) {
		stageViewport = new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
	    stage = new Stage(stageViewport, spriteBatch); //create stage with the stageViewport and the SpriteBatch given in Constructor
	    texture = new Texture(Gdx.files.internal("Fighter-Front.gif"));
	    background = new Texture(Gdx.files.internal("brown rectangle.jfif"));
	    drawablebackground = new TextureRegionDrawable((new TextureRegion(background)));
	    drawable = new TextureRegionDrawable((new TextureRegion(texture)));
	    this.game = game;
	    //this.gamescreen = new GameScreen(game);
	   // this.MPSS = new MultiplayerStartScreen(game);
	    this.mainmenu = mainmenu;
	    

	    table = new Table();
	    table.setVisible(true);
	    
	   
       ImageButton imageButton = new ImageButton(drawable);
       imageButton.setPosition(Gdx.graphics.getWidth() / 2, 900);
       imageButton.addListener( new ClickListener() {              
    	    @Override
    	    public void clicked(InputEvent event, float x, float y) {
    		    gamescreen = new GameScreen(game);

    	    	game.setScreen(gamescreen);
    	    	mainmenu.dispose();
    	    	
    	    	
     	    };
    	});
       
       ImageButton imageButton2 = new ImageButton(drawable);
       imageButton2.setPosition(Gdx.graphics.getWidth() / 2, 800);
       imageButton2.addListener( new ClickListener() {              
    	    @Override
    	    public void clicked(InputEvent event, float x, float y) {
    		    MPSS = new MultiplayerStartScreen(game);

    	    	game.setScreen((MPSS));
    	    	mainmenu.dispose();
    	    	/*try {
    				WarpClient.getInstance().connectWithUserName("Jonathan");
    			} catch (Exception e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			} */
    	    };
    	});
       
       ImageButton imageButton3 = new ImageButton(drawable);
       imageButton3.setPosition(Gdx.graphics.getWidth() / 2, 700);
       imageButton3.addListener( new ClickListener() {              
    	    @Override
    	    public void clicked(InputEvent event, float x, float y) {
    	    	
    	    };
    	});
       
       ImageButton imageButton4 = new ImageButton(drawable);
       imageButton4.setPosition(Gdx.graphics.getWidth() / 2, 600);
       imageButton4.addListener( new ClickListener() {              
    	    @Override
    	    public void clicked(InputEvent event, float x, float y) {
    	    	
    	    };
    	});
       
       ImageButton imageButton5 = new ImageButton(drawable);
       imageButton5.setPosition(Gdx.graphics.getWidth() / 2, 500);
       imageButton5.addListener( new ClickListener() {              
    	    @Override
    	    public void clicked(InputEvent event, float x, float y) {
    	        System.out.println("dd");
    	    };
    	});
       
     
       
              	
        //stage.addActor(imageButton);
        table.addActor(imageButton);
        table.addActor(imageButton2);
        table.addActor(imageButton3);
        table.addActor(imageButton4);
        table.addActor(imageButton5);

        stage.addActor(table);
        //stage.addActor(imageButton2);
	}
	
	public Stage getStage() { return stage; }
	


	

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
	
	
	
	

}
