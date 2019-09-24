package com.game1;

import java.util.ArrayList;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Node {
	ArrayList<Node> adjecent = new ArrayList<Node>();
	int reuse_index;
	int x;
	int y;
	int index;
	static int nextindex = 1;
	Vector2 pos = new Vector2();
	Rectangle body;
	/*
	Node right = null;
	Node left = null;
	Node up = null;
	Node down = null;
	Node upright = null;
	Node upleft = null;
	Node downright = null;
	Node downleft = null;
	*/
	Node parent;
	boolean ACNode = false;
	boolean BN = false;
	boolean PN = false;
	double f;
	double g;
	double h;
	double cost;
	
	GameScreen gamescreen;

	public Node(int x, int y, GameScreen gamescreen) {
		pos.x = x;
		pos.y = y;
		//gamescreen.camera.unproject(pos);
		this.x = x;
		this.y = y;
		this.gamescreen = gamescreen;
		
		
		this.index = nextindex;
		increment();
		body = new Rectangle(x - 15, y - 15 ,30,30);
		/*for(Node node : gamescreen.allnodes) {
			if(Intersector.overlaps(node.body, new Rectangle(this.x - 32, this.y, 100,100))) {
				this.right = node;
			}*/	
	}
	
	public void increment() {
		nextindex ++;
	}
	
	
	public void makeNabour() {
		for(Node node : gamescreen.allnodes) {
			if(node.x == this.x - 32 && node.y == this.y) {
				adjecent.add(node);
			}
			if(node.x - 32 == this.x  && node.y == this.y) {
				adjecent.add(node);

			}
			if(node.x == this.x && node.y == this.y - 32) {
				adjecent.add(node);

			}
			if(node.x == this.x && node.y - 32 == this.y) {
				adjecent.add(node);

			}	
			if(node.x + 32 == this.x && node.y + 32 == this.y) {
				adjecent.add(node);

			}
			if(node.x - 32 == this.x && node.y + 32 == this.y) {
				adjecent.add(node);

			}
			if(node.x + 32 == this.x && node.y - 32 == this.y) {
				adjecent.add(node);

			}
			if(node.x - 32 == this.x && node.y - 32 == this.y) {
				adjecent.add(node);

			}
		}

		
		
	}

	
	
}
