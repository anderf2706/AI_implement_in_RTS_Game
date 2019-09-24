package com.game1;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game1 extends Game {
	public SpriteBatch batch;
	Texture img;
	BitmapFont font;
	Texture frontimg;
	
	float x = 0;
	float y = 0;
	
	@Override
	public void create () {
		/*WarpClient.initialize("ae065f75995631d6c9007ec23407e3b16c6effae11bf9b13c4aba099cde39717",
				"71755b14cd2610f6cbefc97e0ed5112d4c68829b4a390ede7138046c519e095a");
		
		try {
			WarpClient myGame = WarpClient.getInstance();
			myGame.addConnectionRequestListener(new ConListen()); 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */
		
		
		
		
		batch = new SpriteBatch();
		//img = new Texture("badlogic.jpg");
		font = new BitmapFont();
		font.setColor(Color.BLACK);
		font.getData().setScale(5);
		frontimg = new Texture(Gdx.files.internal("petemcnally_blog_bergs_var_04.jpg"));
		this.setScreen(new StartScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	
	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
		
	}
}
