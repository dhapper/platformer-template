package level;

import java.awt.Graphics;

import java.util.ArrayList;

import entities.Entity;
import graphics.LevelObjectAnimation;
import main.Game;
import utilz.Constants;

import static utilz.Constants.General.*;

public class LevelManager {
	
	public static boolean SHOW_HITBOXES = false;
	
	private Game game;
	private MapManager mapManager;
	
	private ArrayList<Entity> entities, enemies, tiles;
	private ArrayList<LevelObjectAnimation> levelObjects;
	
	// camera offset vars
	private int xLocationOffset;//, yLocationOffset;
	private int leftBorder = (int) (0.4 * SCREEN_WIDTH);
	private int rightBorder = (int) (0.6 * SCREEN_WIDTH);
	private int locationTilesWide;
	private int maxTilesOffsetX;
	private int maxLocationOffsetX;
	
	public LevelManager(Game game) {
		this.game = game;
		
		entities = new ArrayList<Entity>();
		enemies = new ArrayList<Entity>();
		tiles = new ArrayList<Entity>();
		levelObjects = new ArrayList<LevelObjectAnimation>();
		
		mapManager = new MapManager(this);
		
		game.getPlayer().importLevelManager(this);
		addEntityToList(entities, game.getPlayer());
		
		// update screen offset vars
		locationTilesWide = mapManager.getMap()[0].length;
		maxTilesOffsetX = locationTilesWide - GAME_TILES_WIDE;
		maxLocationOffsetX = maxTilesOffsetX * Constants.TileConstants.TERRAIN_TILE_SIZE;
		
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
	    	
	    
	    updateOffset();
	}
	
	public void render(Graphics g) {
		mapManager.render(g, xLocationOffset);
		
		for(Entity e : enemies)
			e.render(g, xLocationOffset);
		
		game.getPlayer().render(g, xLocationOffset);
		
		for (LevelObjectAnimation loa : levelObjects)
			loa.render(g, xLocationOffset);

	}
	
	private void updateOffset() {
		int playerX = (int) game.getPlayer().getHitbox().x;
	    int xDiff = playerX - xLocationOffset;
	    
	    if(xDiff > rightBorder)
	        xLocationOffset += xDiff - rightBorder;
	    else if(xDiff < leftBorder)
	        xLocationOffset += xDiff - leftBorder;
	    
	    // clamp to max/min offsets
	    if(xLocationOffset > maxLocationOffsetX)
	        xLocationOffset = maxLocationOffsetX;
	    if (xLocationOffset < 0)
	        xLocationOffset = 0;
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
