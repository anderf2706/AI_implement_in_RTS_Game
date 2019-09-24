package com.game1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import org.omg.PortableInterceptor.Interceptor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Player implements Screen, InputProcessor{
	
	GameScreen gamescreen;
	Game1 game;
	
	Rectangle the_player;
	Rectangle future_the_player;	
	Rectangle future_the_player2;
	
	Node endnode;
	
	int dush = 0;
	int rightnum;
	int leftnum ;
	int upnum;
	int downnum;
	
	int health;
	int attack;
	int defense;
	
	Vector2 nodeFin;
	
	Texture playersprite;
	Texture green;
	Texture blue;
	
	boolean executed = false;
	boolean colliding = false;
	
	MapLayer obstacles;
	int objectLayerId = 2;
	
	Vector3 playerlocation;
	
    
    ArrayList<Node> finalpath = new ArrayList<Node>();
    
    Node roadsplit;
	
	float speed = 200;
	Vector2 direction;
	Vector2 new_direction;
	//Vector2 new_direction2;
	
	float distance;
	
	BitmapFont font = new BitmapFont();
	Timer t;
	
	int i;
	
	boolean playerChosen = false;
	boolean moving = false;
	boolean following = false;
	
	
	Node playerNode;
	int k;
	
	MapObjects objects;
	
	float oldX;
	float oldY;
	
	public Player(GameScreen gamescreen, Game1 game, int x, int y) {
		this.gamescreen = gamescreen;
		this.game = game;
		
		the_player = new Rectangle();
		
		objects = gamescreen.map.getLayers().get(1).getObjects();
		future_the_player = new Rectangle();
		
		
		the_player.x = x - 15;
		the_player.y = y - 15;
		the_player.height = 30;
		the_player.width = 30;
		//future_the_player2 = new Rectangle();
		
		playerlocation = new Vector3(0,0,0);
		
		health = 100;
		attack = 10;
		defense = 10;
		
	    
	    
		
		

		
		
	
		
		playersprite = new Texture(Gdx.files.internal("Fighter-Front.gif"));
		green = new Texture(Gdx.files.internal("green.jpg"));
		blue = new Texture(Gdx.files.internal("blue.png"));

		
		
		for(Node node : gamescreen.allnodes) {
			if(Intersector.overlaps(node.body, the_player)) {
				this.playerNode = node;
				node.PN = true;
				//System.out.println(playerNode.x +  " " + playerNode.y + "");
				//System.out.println("" + playerNode.down + playerNode.left + playerNode.right + playerNode.up);
				//System.out.println(playerNode.down.x + " " + playerNode.down.y);
				//System.out.println(playerNode.index);
				//System.out.println("cool");
				break;
			}
		}
		
		gamescreen.players.add(this);
		
	}
	
	public void collision() {
		for (int i = 0; i < gamescreen.players.size(); i++) {
			if(Intersector.overlaps(gamescreen.players.get(i).the_player, this.the_player) && !gamescreen.players.get(i).equals(this)) {
				//stopmove();	
				colliding = true;
				
			}
			
		}
	}
	
	
	
	
	
	public void move() {
		dush = 0;
		if(dush < finalpath.size()) {
			t = new Timer();
			t.schedule(new TimerTask() {
	
				@Override
				public void run() {
					// TODO Auto-generated method stub
					
					doo(dush);
					
			    
			    	
			    }
			}, (long)0, (long)500);
			
		}
		else {
			//t.cancel();
		}
	}
		
	public void doo(int b) {
			this.the_player.x = finalpath.get(b).x - the_player.width/2;
			this.the_player.y = finalpath.get(b).y - the_player.height/2;
			
			if(colliding) {
				
			}
			
			dush++;
		
	}
	
	
	public void follow(final Player player) {
		dush = 0;
		if(dush < finalpath.size()) {
			t = new Timer();
			t.schedule(new TimerTask() {
	
				@Override
				public void run() {
					// TODO Auto-generated method stub
					
					duu(dush, player);
					
			    
			    	
			    }
			}, (long)0, (long)500);
			
		}
	}
	
	public void duu(int b, Player player) {
		Node nextnode = finalpath.get(0);

		this.the_player.x = nextnode.x - 25;
		this.the_player.y = nextnode.y - 25;
		A_star astar = new A_star(gamescreen);
		
		finalpath = astar.pathfinder(player.playerNode, this.endnode);
	
}

		
		
		
	public void stopmove() {
		t.cancel();
	}




	
	
	public void makevector() {
		//if() {
		gamescreen.map.getTileSets().getTile(1).getProperties();
		//}
	}
	

	
	


	@Override
	public boolean keyDown(int keycode) {
		
		
		return false;
	}




	@Override
	public boolean keyUp(int keycode) {
		
		return false;
	}




	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void overlap(int button) {
		if((Intersector.overlaps(gamescreen.the_mouse, the_player) && button != Input.Buttons.RIGHT)){
			playerChosen = true;
			/*"Player Chosen \n"
					+"health:" + this.health + "\n"
					+"attack" + this.attack + "\n"
					+"defence" + this.defense);
			*/
		
		}
		if(!(Intersector.overlaps(gamescreen.the_mouse, the_player)) && (button == Input.Buttons.LEFT)) {
			playerChosen = false;
			/*JTextArea jTextArea = new JTextArea();
			jTextArea.append( "Player UnChosen" );

			gamescreen.frame.add( jTextArea );*/
		}
	}




	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
			overlap(button);
			
			if(playerChosen) {
				if(button == Input.Buttons.RIGHT) {
					//System.out.println("klar");
					
					endnode = gamescreen.chosenNode;
					if(!executed) {
						if(t != null) {
						t.cancel();
						}
						following = false;
						//System.out.println("klar2");
						
					
						
						moving = true;						
						
						
						
					
						//a_Star();
						System.out.println("starting new");
							A_star astar = new A_star(gamescreen);
							finalpath = astar.pathfinder(this.playerNode, this.endnode);
							System.out.println("inne");
						
						
						/*for (Player player : gamescreen.players) {
							if( Intersector.overlaps(player.the_player, gamescreen.chosenNode.body)) {
								Player target = player;
								following = true;
								follow(target);
							}
						}*/
						for (Player player : gamescreen.players) {
							if(Intersector.overlaps(gamescreen.the_mouse, player.the_player)){
								Player target = player;
								following = true;
								follow(target);
								break;
							}
									
						}
						if(!following) {
							move();
						}
						//make_trail();
						/*System.out.println(end);
						System.out.println(distance);
						System.out.println(direction.angle());
						System.out.println(start);*/
						executed = true;
					}
				}
			}
		return false;
		
	}




	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		executed = false;
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




	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
	
	public void batch(SpriteBatch batch) {
		
		batch.draw(playersprite, this.the_player.x, this.the_player.y, this.the_player.width, this.the_player.height);
		
		if(playerChosen) {
			batch.draw(green, the_player.x, the_player.y + 75, 50, 10);
			font.draw(batch, "" + health, this.the_player.x + 20, this.the_player.y + 70);
			
			
		}
		
		if(gamescreen.chosenNode != null) {
			batch.draw(green, gamescreen.chosenNode.x, gamescreen.chosenNode.y, 5, 5);
		
		}
		if(playerNode != null) {
			batch.draw(green, playerNode.x, playerNode.y, 5, 5);
		
		/*for(Node node : closedlist) {
			batch.draw(green, node.x, node.y, 5, 5);
		}*/
		/*if(gamescreen.chosenNode != playerNode && playerChosen) {	
			for(Node node : finalpath) {
				font.draw(batch, "" + finalpath.indexOf(node), node.x, node.y + 50);
				batch.draw(green, node.x, node.y, 5, 5);
			}
		}*/
			
			/*batch.draw(green, playerNode.right.x, playerNode.right.y, 5, 5);
			batch.draw(green, playerNode.left.x, playerNode.left.y, 5, 5);
			batch.draw(green, playerNode.up.x, playerNode.up.y, 5, 5);
			batch.draw(green, playerNode.down.x, playerNode.down.y, 5, 5);
			*/
		}
		
		
		
	}




	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		 
		  
		  check_player();
		  	collision();
		  	
		  
		  
		  
	}
	
	public void check_player() {
		 if(!Intersector.overlaps(the_player, playerNode.body)) {
			  for (Node node : gamescreen.allnodes) {
					
		  			if (Intersector.overlaps(node.body, the_player)) {
		  				this.playerNode = node;
		  				break;
		  			}
		  		}
		  }
		 if(t != null && (Intersector.overlaps(endnode.body, this.the_player))) {
				stopmove();
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
		// TODO Auto-generated method stub
		
	}

	
}

