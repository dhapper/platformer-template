package graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.EnumMap;

import enums.BgColour;
import utilz.Constants;
import utilz.LoadSave;

import static utilz.Constants.General.*;
import static utilz.Constants.TileConstants.TERRAIN_TILE_SIZE;

public class BackgroundHelper {

	private static final EnumMap<BgColour, BufferedImage> backgrounds = new EnumMap<>(BgColour.class);

	static {
	    load();
	}
	
	// to prevent instantiation
	private BackgroundHelper() {}

	public static void load() {
		for (BgColour c : BgColour.values())
		    backgrounds.put(c, LoadSave.ImportImg(Constants.ResourcePaths.BACKGROUND + c.file));
	}

	public static BufferedImage get(BgColour colour) {
		return backgrounds.get(colour);
	}
	
	public static void paintBackground(Graphics g, BgColour bgColour) {
	    BufferedImage bgTile = get(bgColour);
	    for (int i = 0; i < GAME_TILES_WIDE; i++)
	        for (int j = 0; j < GAME_TILES_HIGH; j++)
	        	g.drawImage(bgTile, i * TERRAIN_TILE_SIZE, j * TERRAIN_TILE_SIZE, TERRAIN_TILE_SIZE, TERRAIN_TILE_SIZE, null);
	}


}
