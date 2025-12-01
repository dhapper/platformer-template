package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import entities.Tile;
import utilz.Constants;
import utilz.LoadSave;

import static utilz.Constants.TileConstants.*;

public class MapManager {
	
	// 22x11 tile sheet
	private int sheetTilesWide = 22;
	private int sheetTilesLong = 11;
	
	private BufferedImage tileSheet;
	private BufferedImage[] tiles;
	
	private Tile[][] tileMap;
	
	private int[][] map = {
			{5, 5, 5, 5, 5, 5, 5, 5, 5, 5},
			{5, 5, 5, 5, 5, 5, 5, 5, 5, 5},
			{5, 5, 5, 5, 5, 5, 5, 5, 5, 5},
			{5, 5, 5, 5, 5, 5, 5, 5, 5, 5},
		    {5, 5, 5, 5, 5, 5, 5, 0, 2, 5},
		    {5, 5, 5, 5, 5, 5, 0, 26, 24, 5},
		    {5, 5, 5, 5, 5, 5, 44, 45, 46, 5},
		    {5, 6, 7, 7, 8, 5, 5, 5, 5, 5},
		    {5, 28, 29, 29, 30, 5, 5, 5, 5, 5}
		};
	
	private LevelManager levelManager;
	
	public MapManager(LevelManager levelManager) {
		this.levelManager = levelManager;
		
		tileSheet = LoadSave.ImportImg(Constants.ResourcePaths.TILES);
		
		prepTiles();
		prepTileMap();
//		levelManager
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
//	            g.drawImage(tiles[map[j][i]], TERRAIN_TILE_SIZE * i, TERRAIN_TILE_SIZE * j, TERRAIN_TILE_SIZE, TERRAIN_TILE_SIZE, null);
	        }
	    }
	}
	
	public void render(Graphics g) {
	    int rows = tileMap.length;
	    int cols = tileMap[0].length;
	    for (int j = 0; j < rows; j++) {
	        for (int i = 0; i < cols; i++) {
//	            g.drawImage(tiles[map[j][i]], TERRAIN_TILE_SIZE * i, TERRAIN_TILE_SIZE * j, TERRAIN_TILE_SIZE, TERRAIN_TILE_SIZE, null);
	        	Tile currentTile = tileMap[j][i];
	        	if(currentTile.getHitbox() != null) {
//	        		g.drawImage(tiles[currentTile.getId()], TERRAIN_TILE_SIZE * i, TERRAIN_TILE_SIZE * j, TERRAIN_TILE_SIZE, TERRAIN_TILE_SIZE, null);
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
