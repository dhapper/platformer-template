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
	
	private ArrayList<Entity> entities;
	private ArrayList<LevelObjectAnimation> levelObjects;
	
	AngryPig ap;
	
	public LevelManager(Game game) {
		this.game = game;
		
		entities = new ArrayList<Entity>();
		levelObjects = new ArrayList<LevelObjectAnimation>();
		
		mapManager = new MapManager(this);
		
		game.getPlayer().importLevelManager(this);
		addEntityToList(game.getPlayer());
		
		ap = new AngryPig(200, 100);
		ap.importLevelManager(this);
		addEntityToList(ap);
		
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
		
		for (LevelObjectAnimation loa : levelObjects) {
			loa.render(g);
		}
		
		ap.render(g);

	}
	
	public void addEntityToList(Entity entity) {
		entities.add(entity);
	}
	
	public void addLevelObject(LevelObjectAnimation obj) {
		levelObjects.add(obj);
	}

}
