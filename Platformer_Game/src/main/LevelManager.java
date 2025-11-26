package main;

import java.awt.Graphics;

public class LevelManager {
	
	private Game game;
	private MapManager mapManager;
	
	public LevelManager(Game game) {
		this.game = game;
		
		mapManager = new MapManager();
		
	}
	
	public void render(Graphics g) {
		mapManager.render(g);
	}

}
