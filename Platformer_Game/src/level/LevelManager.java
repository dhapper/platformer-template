package level;

import java.awt.Graphics;
import java.util.ArrayList;

import entities.AngryPig;
import entities.Entity;
import entities.LivingEntity;
import graphics.LevelObjectAnimation;
import main.Game;

public class LevelManager {
	
	public static boolean SHOW_HITBOXES = false;
	
	private Game game;
	private MapManager mapManager;
	
	private ArrayList<Entity> entities, enemies, tiles;
	private ArrayList<LevelObjectAnimation> levelObjects;
	
	public LevelManager(Game game) {
		this.game = game;
		
		entities = new ArrayList<Entity>();
		enemies = new ArrayList<Entity>();
		tiles = new ArrayList<Entity>();
		levelObjects = new ArrayList<LevelObjectAnimation>();
		
		mapManager = new MapManager(this);
		
		game.getPlayer().importLevelManager(this);
		addEntityToList(entities, game.getPlayer());
		
	}
	
	public void update() {
	    for (int i = levelObjects.size() - 1; i >= 0; i--) {
	        LevelObjectAnimation loa = levelObjects.get(i);
	        loa.update();
	        if (loa.isFinished()) {
	            levelObjects.remove(i);
	        }
	    }
	    
	    for(Entity e : enemies)
	    	e.update();
	    	
	}
	
	public void render(Graphics g) {
		mapManager.render(g);
		
		for(Entity e : enemies)
			e.render(g);
		
		for (LevelObjectAnimation loa : levelObjects)
			loa.render(g);

	}
	
	public void addEntityToList(ArrayList<Entity> list, Entity entity) {
		list.add(entity);
	}
	
	public void addLevelObject(LevelObjectAnimation obj) {
		levelObjects.add(obj);
	}
	
	// getters and setters
	
	public Game getGame() {
		return game;
	}
	
	public ArrayList<Entity> getEntities(){
		return entities;
	}
	
	public ArrayList<Entity> getEnemies(){
		return enemies;
	}
	
	public ArrayList<Entity> getTiles(){
		return tiles;
	}
}
