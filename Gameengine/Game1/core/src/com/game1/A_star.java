package com.game1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;

import com.badlogic.gdx.math.Vector2;

public class A_star{
	
	int round;
	GameScreen gamescreen;
	
	
	public A_star(GameScreen gamescreen) {
		this.gamescreen = gamescreen;
	}
	
	public ArrayList<Node> pathfinder(Node Start, Node End) {//A*-algoritmen. Setter start og end node med findnode metoden. 
		
		Node end = End;
		ArrayList<Node> closedList = new ArrayList<Node>(); //generer listene som skal brukes. 
		ArrayList<Node> openList = new ArrayList<Node>();
		Node start = Start;
		start.g = 0;//setter g til start lik null så den kan regnes ut i Node klassen. 
		openList.add(start);//adder start til nodelisten 
		Node current_Node;
		int i= 0;
		while(!closedList.contains(end)) {//kjører loopen til vi har funnet mål 
			
			i = i + 1;
			if(openList.size() > 1) {
				Collections.sort(openList, new Comparator<Node>() {
					@Override
					public int compare(Node n1, Node n2) {
						return Double.compare(n1.f, n2.f);
					}
				});
			}
			closedList.add(0, openList.get(0));//flytter den beste noden over til closedlist, og av openlist
			openList.remove(0);
			
			current_Node = closedList.get(0);
			for (Node node : current_Node.adjecent) {//går igjennom alle nodene vedsidenav denne som er lovelige
				
				
				if(openList.contains(node)) {//hvis noden er på openlist allerede betyr det at den har regnet ut f allerede. her sjekker vi om den nye 
						if(current_Node.g + 1 < node.g) {//ruten er bedre en den forrige og kalkulerer f på nytt og bytter parent
							node.parent = current_Node;
							if(node.x != node.parent.x && node.y != node.parent.y) {
								node.cost = Math.sqrt(2);
								System.out.println("sqrt");
							}
							else {
								node.cost = 1;
								System.out.println("notsqrt");
							}
							node.g = node.parent.g + node.cost;
							node.h = Math.max(Math.abs(node.x - end.x), Math.abs(node.y - end.y));
							node.f = node.h + node.g;
							System.out.println("a");
							Collections.sort(openList, new Comparator<Node>() {
								@Override
								public int compare(Node n1, Node n2) {
								   return Double.compare(n1.f, n2.f);
								 }
							});
						}
				}
				
				if(closedList.contains(node) ) {//hvis noden er på closedlist skal vi ignorere den å hoppe videre med continue
					System.out.println("c");
					continue;
					
				}
				if(!openList.contains(node)) {//hvis noden ikke er på opnelist skal vi legge den inn, gi den parentnode som current og regne ut f.
					System.out.println("d");
					openList.add(node);
					node.parent = current_Node;
					if(node.x != node.parent.x && node.y != node.parent.y) {
						node.cost = Math.sqrt(2);
						System.out.println("sqrt");
					}
					else {
						node.cost = 1;
						System.out.println("1");
					}
					node.g = node.parent.g + node.cost;
					node.h = Math.max(Math.abs(node.x - end.x), Math.abs(node.y - end.y));
					node.f = node.h + node.g;
					
				}
			
						
							
			}		
					
				
		}	
				
		
		ArrayList<Node> finalPath = new ArrayList<Node>();//lager listen vi legger de fungerende nodene i for å få final path.
		finalPath.add(end);//legger til endnode 
		while(!finalPath.contains(start)) {//kjører loopen til vi har startnode i listen. Da har vi funnet veien 
			finalPath.add(0, finalPath.get(0).parent); //legger her til parent til den første noden for å jobbe seg bakover. 
		}
		 //setter tilslutt listen lik variabelen for classen. 
		
		return finalPath;
		
			
			
			
	
	}

	
	


}
