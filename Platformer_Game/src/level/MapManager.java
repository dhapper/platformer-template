package level;

import static utilz.Constants.TileConstants.DEFAULT_TERRAIN_TILE_SIZE;
import static utilz.Constants.TileConstants.TERRAIN_TILE_SIZE;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import entities.Tile;
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
	
	private LevelManager levelManager;
	
	public MapManager(LevelManager levelManager) {
		this.levelManager = levelManager;
		
		map = MapLoader.LoadMapFromCSV("res/Levels/LEVEL_1.csv");
		
		tileSheet = LoadSave.ImportImg(Constants.ResourcePaths.TILES);
		
		prepTiles();
		prepTileMap();
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
	        	levelManager.addEntityToList(tileMap[j][i]);
	        }
	    }
	}
	
	public void render(Graphics g) {
		
		g.setColor(new Color(200, 200, 220));
		g.fillRect(0, 0, Constants.General.SCREEN_WIDTH, Constants.General.SCREEN_HEIGHT);
		
	    int rows = tileMap.length;
	    int cols = tileMap[0].length;
	    for (int j = 0; j < rows; j++) {
	        for (int i = 0; i < cols; i++) {
	        	Tile currentTile = tileMap[j][i];
	        	if(currentTile.getHitbox() != null) {
	        		g.drawImage(
	        				tiles[currentTile.getId()],
	        				(int) currentTile.getHitbox().x,
	        				(int) currentTile.getHitbox().y,
	        				(int) currentTile.getHitbox().width,
	        				(int) currentTile.getHitbox().height,
	        				null);
	        		if(LevelManager.SHOW_HITBOXES) { currentTile.drawHitbox(g); }
	        	}
	        }
	    }
	}
	

}
