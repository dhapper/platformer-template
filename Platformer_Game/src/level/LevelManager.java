package level;

import java.awt.Graphics;
import java.util.ArrayList;

import entities.AngryPig;
import entities.Entity;
import graphics.LevelObjectAnimation;
import main.Game;

public class LevelManager {
	
	public static boolean SHOW_HITBOXES = false;
	
	private Game game;
	private MapManager mapManager;
	
	private ArrayList<Entity> entities, enemies;
	private ArrayList<LevelObjectAnimation> levelObjects;
	
	AngryPig ap;
	
	public LevelManager(Game game) {
		this.game = game;
		
		entities = new ArrayList<Entity>();
		enemies = new ArrayList<Entity>();
		levelObjects = new ArrayList<LevelObjectAnimation>();
		
		mapManager = new MapManager(this);
		
		game.getPlayer().importLevelManager(this);
		addEntityToList(entities, game.getPlayer());
		
		ap = new AngryPig(700, 300);
		ap.importLevelManager(this);
		addEntityToList(entities, ap);
		addEntityToList(enemies, ap);
		
		game.getPlayer().setEntities(entities);
		ap.setEntities(entities);
	}
	
	public void update() {
	    for (int i = levelObjects.size() - 1; i >= 0; i--) {
	        LevelObjectAnimation loa = levelObjects.get(i);
	        loa.update();
	        if (loa.isFinished()) {
	            levelObjects.remove(i);
	        }
	    }
	    
	    ap.update();
	}
	
	public void render(Graphics g) {
		mapManager.render(g);
		
		ap.render(g);
		
		for (LevelObjectAnimation loa : levelObjects) {
			loa.render(g);
		}

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

}
