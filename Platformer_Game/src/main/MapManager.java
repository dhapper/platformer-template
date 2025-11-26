package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import utilz.Constants;
import utilz.LoadSave;

import static utilz.Constants.TileConstants.*;

public class MapManager {
	
	// 22x11 tile sheet
	private int sheetTilesWide = 22;
	private int sheetTilesLong = 11;
	
	private BufferedImage tileSheet;
	private BufferedImage[] tiles;
	
	private int[][] map = {
		    {5, 5, 5, 5, 5, 5, 0, 1, 2, 5},
		    {5, 5, 5, 5, 5, 5, 22, 23, 24, 5},
		    {5, 5, 5, 5, 5, 5, 44, 45, 46, 5},
		    {5, 6, 7, 7, 8, 5, 5, 5, 5, 5},
		    {5, 28, 29, 29, 30, 5, 5, 5, 5, 5}
		};
	
	public MapManager() {
		tileSheet = LoadSave.ImportImg(Constants.ResourcePaths.Tiles);
		prepTiles();
	}
	
	public void prepTiles() {
		
		int tileQuantity = sheetTilesWide * sheetTilesLong;
		tiles = new BufferedImage[tileQuantity];
		
		for(int i = 0; i < tileQuantity; i++) {
			
			int col = i % sheetTilesWide;
			int row = i / sheetTilesWide;
			tiles[i] = tileSheet.getSubimage(col * DefaultTerrainTileSize, row * DefaultTerrainTileSize, DefaultTerrainTileSize, DefaultTerrainTileSize);
		}
		
	}
	
	public void render(Graphics g) {
	    int rows = map.length;
	    int cols = map[0].length;
	    for (int j = 0; j < rows; j++) {
	        for (int i = 0; i < cols; i++) {
	            g.drawImage(tiles[map[j][i]], TerrainTileSize * i, TerrainTileSize * j, TerrainTileSize, TerrainTileSize, null);
	        }
	    }
	}
	

}
