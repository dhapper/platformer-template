package main;

import java.awt.Graphics;
import java.util.ArrayList;

import entities.Entity;

public class LevelManager {
	
	private Game game;
	private MapManager mapManager;
	
	private ArrayList<Entity> entities;
	
	public LevelManager(Game game) {
		this.game = game;
		
		entities = new ArrayList<Entity>();
		
		mapManager = new MapManager(this);
		
		game.getPlayer().setEntities(entities);
	}
	
	public void render(Graphics g) {
		mapManager.render(g);
	}
	
	public void addEntityToList(Entity entity) {
		entities.add(entity);
	}

}
