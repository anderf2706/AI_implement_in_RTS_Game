package com.game1;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class GameScreen extends ApplicationAdapter implements Screen, InputProcessor{

	Rectangle the_mouse;
	Vector2 mouseInWorld2D;
    Vector3 mouseInWorld3D;
    Vector2 start;
	Vector2 end;
	Node chosenNode;
	float touchX;
	float touchY;
	
	boolean makeP = false;
	boolean makeB = false;
	
	Hud hud;
	
	boolean makeBarracks = false;
	boolean makeHouse = false;
	boolean makeWall = false;
	boolean makeCastle = false;
	boolean menu = false;
	boolean rotate1 = false;
	boolean rotate2 = false;
	
	BitmapFont font;
	
	
	
	Game1 game;
	OrthographicCamera camera;
	OrthogonalTiledMapRenderer renderer;
	SpriteBatch batch;
	
	TiledMap map;
	TiledMapTileLayer tilelayer;
	int tileSize;
	int mapWidth;  
	int mapHeight;
	AssetManager manager;
	
	ArrayList<Node> allnodes = new ArrayList<Node>();
	ArrayList<Player> players = new ArrayList<Player>();
	ArrayList<Building> buildings = new ArrayList<Building>();

	ShapeRenderer sr;
	
	float up;
	float down;
	float right;
	float left;
	float viewX;
	float viewY;
	
	Texture green;
	Texture blue;
	
	MapObjects objects;
	ArrayList<RectangleMapObject> mapobjects;
	
	InputMultiplexer multiplexer;
	

	
	public GameScreen(Game1 game) {
		
		
		map = new TmxMapLoader().load("nystart.tmx");
		tilelayer = (TiledMapTileLayer) map.getLayers().get(0);
		tileSize = (int) tilelayer.getTileWidth();
		mapWidth = tilelayer.getWidth() * tileSize;  
		mapHeight = tilelayer.getHeight() * tileSize;
		
		this.game = game;
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		green = new Texture(Gdx.files.internal("green.jpg"));
		blue = new Texture(Gdx.files.internal("blue.png"));
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, w, h);
		camera.update();
		objects = map.getLayers().get(1).getObjects();              //<- HER MAN VELGER LAYER 
		System.out.println(objects.getCount());
		
		mapobjects = new ArrayList<RectangleMapObject>();
		sr = new ShapeRenderer();
		makenodes(mapobjects, objects);
		
		font = new BitmapFont();
		
		hud = new Hud(game.batch, this);
		
		
		
		multiplexer = new InputMultiplexer();
		
		multiplexer.addProcessor(0, hud.stage);
		multiplexer.addProcessor(1, this);
		

		
		
		
		
	
		
		
		
		
		
		
		renderer = new OrthogonalTiledMapRenderer(map);
		
		
		
		the_mouse = new Rectangle();
		the_mouse.height = 2;
		the_mouse.width = 2;
		
		Vector2 mouseInWorld2D = new Vector2();
	    Vector3 mouseInWorld3D = new Vector3();
	    this.mouseInWorld2D = mouseInWorld2D;
	    this.mouseInWorld3D = mouseInWorld3D;
	    
	     
	}
	
	public void makenodes(ArrayList<RectangleMapObject> mapobjects, MapObjects objects) {
		for (int i = 16; i < mapWidth; i += 32) {
			for (int j = 16; j < mapHeight; j += 32) {
				for (RectangleMapObject rectangleObject : objects.getByType(RectangleMapObject.class)) {
					mapobjects.add(rectangleObject);
				
				    
				}
				int good = 0;
				for (int k = 0; k < mapobjects.size(); k++) {
					if (!Intersector.overlaps(mapobjects.get(k).getRectangle(), new Rectangle(i,j,1,1))) {
				    	good += 1;
					}
					continue;
					
				}
				if(good == mapobjects.size()) {
					allnodes.add(new Node(i,j, this));
				}
				continue;
			}
		}
		for(Node node : allnodes) {
			node.makeNabour();
		}
	}
	
	
	
	@Override
	public void show() {
		Gdx.input.setInputProcessor(multiplexer);


		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
      
       
        mouseInWorld3D.x = Gdx.input.getX();
		mouseInWorld3D.y = Gdx.input.getY();
		mouseInWorld3D.z = 0;
		camera.unproject(mouseInWorld3D);
		mouseInWorld2D.x = mouseInWorld3D.x;
		mouseInWorld2D.y = mouseInWorld3D.y;
		  
		the_mouse.x = mouseInWorld2D.x;
		the_mouse.y = mouseInWorld2D.y;
        
		for(Player player : players) {
			player.render(delta);
		}
		for(Building building : buildings) {
			building.render(delta);
		}       
		
        viewX = right + left;
        viewY = up + down;
        game.batch.setProjectionMatrix(camera.combined);
        camera.translate(viewX, viewY);
        camera.update();
        renderer.setView(camera);
        renderer.render();
        if(rotate1) {
        	rotate(40);
        }
        if(rotate2) {
        	rotate(-40);
        }
        
        
        game.batch.begin();
        
        
        for(Player player : players) {
			player.batch(game.batch);
			
		}
        for(Building building : buildings) {
			building.batch(game.batch);
		}
        
       
        if(chosenNode != null) {
        	game.batch.draw(green, chosenNode.x - 8, chosenNode.y - 8, 16, 16);
        }
        	
		
        
        
		game.batch.end();
		
		// game.batch.setProjectionMatrix(hud.getStage().getCamera().combined); //set the spriteBatch to draw what our stageViewport sees
	     hud.getStage().act(delta); //act the Hud
	     hud.getStage().draw(); //draw the Hud
		
	  
        
        
        
        
        
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
		 //dispose our hud
	}
	
	public void makeP() {
		try {
			new Player(this, game, chosenNode.x, chosenNode.y);
			
		}
		catch(Exception e) {
			
		}
				
		
	}
	
	public void makeBarracks() {
		
				for (int i = 0; i < this.buildings.size(); i++) {
					
					if((Intersector.overlaps(new Rectangle(chosenNode.x - 32, chosenNode.y - 32, 64, 64), buildings.get(i).the_building))){
						
						return;
					}
				}
				
				for (Node node : chosenNode.adjecent) {
					if(node == null) {
						return;
					}
					
				}
				new Barracks(this, chosenNode.x, chosenNode.y);
		
	}
	
	public void makeHouse() {
		
		for (int i = 0; i < this.buildings.size(); i++) {
			
			if((Intersector.overlaps(new Rectangle(chosenNode.x - 16, chosenNode.y - 16, 32, 32), buildings.get(i).the_building))){
				
				return;
			}
		}
		
		
		if(chosenNode == null) {
			return;
		}
			
		
		new House(this, chosenNode.x, chosenNode.y);

	}
	
		public void makeWall() {
			
			for (int i = 0; i < this.buildings.size(); i++) {
				
				if((Intersector.overlaps(new Rectangle(chosenNode.x - 16, chosenNode.y - 16, 32, 32), buildings.get(i).the_building))){
					
					return;
				}
			}
			
			if(chosenNode == null) {
				return;
			}
				
			
			new Wall(this, chosenNode.x, chosenNode.y);
	
	}
		
public void makeCastle() {
			
			for (int i = 0; i < this.buildings.size(); i++) {
				
				if((Intersector.overlaps(new Rectangle(chosenNode.x - 64, chosenNode.y - 64, 128, 128), buildings.get(i).the_building))){
					
					return;
				}
			}
			
			if(chosenNode == null && chosenNode.adjecent.contains(null)) {
				return;
			}
				
			
			new Castle(this, chosenNode.x, chosenNode.y);
	
	}
		
	
		
		
	public void rotate(int b) {
		camera.rotate(b * Gdx.graphics.getDeltaTime());
	}



	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Input.Keys.M) {
			menu= !menu;
			System.out.println("mm");
		}
		if(keycode == Input.Keys.D) {
			right = 600 * Gdx.graphics.getDeltaTime();
		}
		if(keycode == Input.Keys.A) {
			left = -600 * Gdx.graphics.getDeltaTime();
		}
		if(keycode == Input.Keys.W) {
			up = 600 * Gdx.graphics.getDeltaTime();
		}
		if(keycode == Input.Keys.S) {
			down = -600 * Gdx.graphics.getDeltaTime();
		}
		if(keycode == Input.Keys.Q) {
			rotate1 = true;
		}
		if(keycode == Input.Keys.E) {
			rotate2 = true;
		}
		
		if(keycode == Input.Keys.P) {
			
			makeP = !makeP;
			System.out.println(makeP);
		}
		if(keycode == Input.Keys.B) {
			
			makeB = !makeB;
			System.out.println(makeB);
			
		}
		/*if(makeB) {
			switch(keycode) {
			case 1: keycode = Input.Keys.NUM_1;
			makeBarracks = !makeBarracks;
			break;
			case 2: keycode = Input.Keys.NUM_2;
			makeHouse = !makeHouse;
			break;
			case 3:keycode = Input.Keys.NUM_3;
			makeWall = !makeWall;
			break;
			case 4:keycode = Input.Keys.NUM_4;
			makeCastle = !makeCastle;
			break;
			}
		}*/
		
		for(Player player : players) {
			player.keyDown(keycode);
		}
		for(Building building : buildings) {
			building.keyDown(keycode);
		}      
		System.out.println(makeBarracks);
		return false;
	}


	@Override
	public boolean keyUp(int keycode) {
		if(keycode == Input.Keys.D) {
			right = 0;
		}
		if(keycode == Input.Keys.A) {
			left = 0;
		}
			
		if(keycode == Input.Keys.W) {
			up = 0;
			
		}
		if(keycode == Input.Keys.S) {
			down = 0;
			
		}
		if(keycode == Input.Keys.Q) {
			rotate1 = false;
		}
		if(keycode == Input.Keys.E) {
			rotate2 = false;
		}
		//player.keyUp(keycode);
		for(Player player : players) {
			player.keyUp(keycode);
		}
		for(Building building : buildings) {
			building.keyUp(keycode);
		}      
		return false;
	}


	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		
			if(button == Input.Buttons.RIGHT) {
				makeBarracks = false;
				makeHouse = false;
				makeWall = false;
				makeCastle = false;
			}
			float touchX = mouseInWorld2D.x;
			float touchY = mouseInWorld2D.y;
			this.touchX = touchX;
			this.touchY = touchY;
			
			Rectangle mouserec = new Rectangle(the_mouse.x, the_mouse.y, 1,1);
			for (Node node : allnodes) {
				
			    if (Intersector.overlaps(node.body, mouserec)) {
			    	this.chosenNode = node;
			    
			    }
			}
			
			for (Player player : players) {
				player.touchDown(screenX, screenY, pointer, button);
			}
			for(Building building : buildings) {
				building.touchDown(screenX, screenY, pointer, button);
			}      
			
			if(makeP && button == Input.Buttons.LEFT) {
				makeP();
			}
			
			if(makeBarracks && button == Input.Buttons.LEFT) {
				makeBarracks();
			}
			if(makeHouse && button == Input.Buttons.LEFT) {
				makeHouse();
			}
			if(makeWall && button == Input.Buttons.LEFT) {
				makeWall();
			}
			if(makeCastle && button == Input.Buttons.LEFT) {
				makeCastle();
			}
			
		return false;
			
	}


	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		for (Player player : players) {
			player.touchUp(screenX, screenY, pointer, button);
			
		}	
		
		for(Building building : buildings) {
			building.touchUp(screenX, screenY, pointer, button);
		}      
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
