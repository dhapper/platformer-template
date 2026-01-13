package level;

import static utilz.Constants.TileConstants.DEFAULT_TERRAIN_TILE_SIZE;
import static utilz.Constants.TileConstants.TERRAIN_TILE_SIZE;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import entities.AngryPig;
import entities.Tile;
import enums.BgColour;
import graphics.BackgroundHelper;
import utilz.Constants;
import utilz.LoadSave;
import utilz.MapLoader;

public class MapManager {
	
	// 22x11 tile sheet
	private int sheetTilesWide = 22;
	private int sheetTilesLong = 11;
	
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
		
//		loadBackgrounds();
		prepTiles();
		initLevelData();
	}
	
	private void initLevelData(){
		levelDataArray = new LevelData[5];
		levelDataArray[3] = new LevelData(4, 4, BgColour.BLUE);
		levelDataArray[4] = new LevelData(4, 4, BgColour.PINK);
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
	        	if(enemyMap[j][i] == 1) {
	        		AngryPig ap = new AngryPig(TERRAIN_TILE_SIZE * i, TERRAIN_TILE_SIZE * j);
	        		ap.importLevelManager(levelManager);
	        		levelManager.addEntityToList(levelManager.getEntities(), ap);
	        		levelManager.addEntityToList(levelManager.getEnemies(), ap);
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
