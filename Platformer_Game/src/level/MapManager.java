package level;

import static utilz.Constants.TileConstants.DEFAULT_TERRAIN_TILE_SIZE;
import static utilz.Constants.TileConstants.TERRAIN_TILE_SIZE;
import static utilz.Constants.EnemyIDs.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import entities.AngryPig;
import entities.Chicken;
import entities.LivingEntity;
import entities.Tile;
import enums.BgColour;
import graphics.BackgroundHelper;
import utilz.Constants;
import utilz.LoadSave;
import utilz.MapLoader;

public class MapManager {
	
	private int sheetTilesWide;
	private int sheetTilesLong;
	
	private BufferedImage tileSheet;
	private BufferedImage[] tiles;
	
	private Tile[][] tileMap;
	
	private int[][] map;
	private int[][] enemyMap;
	
	private LevelManager levelManager;
	
	private BgColour bgColour = BgColour.PINK;
	
	private LevelData[] levelDataArray;
	
	public MapManager(LevelManager levelManager) {
		this.levelManager = levelManager;
		
		tileSheet = LoadSave.ImportImg(Constants.ResourcePaths.TILES);
		sheetTilesWide = tileSheet.getWidth() / Constants.TileConstants.DEFAULT_TERRAIN_TILE_SIZE;
		sheetTilesLong = tileSheet.getHeight() / Constants.TileConstants.DEFAULT_TERRAIN_TILE_SIZE;
//		System.out.println(sheetTilesWide + " " + sheetTilesLong);
		
//		loadBackgrounds();
		prepTiles();
		initLevelData();
	}
	
	private void initLevelData(){
		levelDataArray = new LevelData[50];
		levelDataArray[3] = new LevelData(4, 4, BgColour.BLUE);
		levelDataArray[4] = new LevelData(4, 4, BgColour.PINK);
		levelDataArray[5] = new LevelData(4, 4, BgColour.PINK);
		levelDataArray[6] = new LevelData(4, 4, BgColour.GRAY);
	}
	
	public void loadLevel(int level) {
		map = MapLoader.LoadMapFromCSV("res/Levels/LEVEL_" + level + ".csv");
		enemyMap = MapLoader.LoadMapFromCSV("res/Levels/ENEMY_MAP_" + level + ".csv");
		prepTileMap();
		loadEnemies();
		bgColour = levelDataArray[level-1].bgColour();
	}
	
	public void prepTiles() {
		
		int tileQuantity = sheetTilesWide * sheetTilesLong;
		tiles = new BufferedImage[tileQuantity];
		
		for(int i = 0; i < tileQuantity; i++) {
			
			int col = i % sheetTilesWide;
			int row = i / sheetTilesWide;
			tiles[i] = tileSheet.getSubimage(col * DEFAULT_TERRAIN_TILE_SIZE, row * DEFAULT_TERRAIN_TILE_SIZE, DEFAULT_TERRAIN_TILE_SIZE, DEFAULT_TERRAIN_TILE_SIZE);
		}
		
	}
	
	public void prepTileMap() {
		int rows = map.length;
	    int cols = map[0].length;
	    tileMap = new Tile[rows][cols];
	    for (int j = 0; j < rows; j++) {
	        for (int i = 0; i < cols; i++) {
	        	tileMap[j][i] = new Tile(TERRAIN_TILE_SIZE * i, TERRAIN_TILE_SIZE * j, map[j][i]);
	        	levelManager.addEntityToList(levelManager.getEntities(), tileMap[j][i]);
	        	levelManager.addEntityToList(levelManager.getTiles(), tileMap[j][i]);
	        }
	    }
	}
	
	
	public void loadEnemies() {
		int rows = map.length;
	    int cols = map[0].length;
	    for (int j = 0; j < rows; j++) {
	        for (int i = 0; i < cols; i++) {
	        	if(enemyMap[j][i] != -1) {
	        		
	        		LivingEntity enemy = null;
	        		
	        		switch (enemyMap[j][i]) {
		        	    case ANGRY_PIG	-> enemy = new AngryPig(TERRAIN_TILE_SIZE * i, TERRAIN_TILE_SIZE * j);
		        	    case CHICKEN	-> enemy = new Chicken(TERRAIN_TILE_SIZE * i, TERRAIN_TILE_SIZE * j);
	        		}
	        		
	        		enemy.importLevelManager(levelManager);
	        		enemy.importPlayer(levelManager.getPlayer());
	        		levelManager.addEntityToList(levelManager.getEntities(), enemy);
	        		levelManager.addEntityToList(levelManager.getEnemies(), enemy);
	        		
	        	}
	        }
	    }
	}
	
	public void render(Graphics g, int xLocationOffset) {
		BackgroundHelper.paintBackground(g, bgColour);
		
	    int rows = tileMap.length;
	    int cols = tileMap[0].length;
	    for (int j = 0; j < rows; j++) {
	        for (int i = 0; i < cols; i++) {
	        	Tile currentTile = tileMap[j][i];
	        	if(currentTile.getHitbox() != null) {
	        		int screenLeft  = xLocationOffset;
	        		int screenRight = xLocationOffset + Constants.General.SCREEN_WIDTH;
	        		
	        		float tileLeft  = currentTile.getHitbox().x;
	        		float tileRight = currentTile.getHitbox().x + currentTile.getHitbox().width;

	        		if (tileRight < screenLeft || tileLeft > screenRight) { continue; }

	        		g.drawImage(
	        				tiles[currentTile.getId()],
	        				(int) currentTile.getHitbox().x - xLocationOffset,
	        				(int) currentTile.getHitbox().y,
	        				TERRAIN_TILE_SIZE,
	        				TERRAIN_TILE_SIZE,
//	        				(int) currentTile.getHitbox().width,
//	        				(int) currentTile.getHitbox().height,
	        				null);
	        		if(LevelManager.SHOW_HITBOXES) { currentTile.drawHitbox(g, xLocationOffset); }
	        	}
	        }
	    }
	}

	public int[][] getMap(){
		return map;
	}
	
	public LevelData[] getLevelDataArray() {
		return levelDataArray;
	}

}
