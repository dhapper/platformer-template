package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import enums.Gamestate;
import enums.Icon;
import graphics.ImageModifier;
import level.LevelManager;
import utilz.Constants;
import utilz.LoadSave;

import static utilz.Constants.UI.LEVEL_BUTTON.*;
import static utilz.Constants.ResourcePaths.LEVEL_BUTTONS;

public class LevelButton extends Button{
	
	private int levelNumber;
	private BufferedImage[] images;
	private LevelManager levelManager;
	
	public LevelButton(int xPos, int yPos, int levelNumber, LevelManager levelManager) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.levelNumber = levelNumber;
		this.levelManager = levelManager;
		
		initBounds(LEVEL_BUTTON_WIDTH, LEVEL_BUTTON_HEIGHT);
		
		loadImage();
	}
	
	private void loadImage() {
		
		String intStr = String.format("%02d", levelNumber);
		String path = LEVEL_BUTTONS + intStr + ".png";
		images = new BufferedImage[2];
		images[0] = LoadSave.ImportImg(path);
		images[1] = ImageModifier.HighlightImage(images[0]);
	}
	
	public void draw(Graphics g) {
		
		int delta = (int) (1 * Constants.General.SCALE);
		
		if (!mouseOver)
		    g.drawImage(images[imageIndex], xPos, yPos, LEVEL_BUTTON_WIDTH, LEVEL_BUTTON_HEIGHT, null);
		else
		    g.drawImage(images[imageIndex], xPos, yPos - delta, LEVEL_BUTTON_WIDTH, LEVEL_BUTTON_HEIGHT, null);

	}
	
//	public void update() {
//		imageIndex = 0;
//		if(mousePressed)
//			imageIndex = 1;
//	}
	
	public void EnterLevel() {
		levelManager.loadLevel(levelNumber);
		Gamestate.state = Gamestate.PLAYING;
	}
	
	public int getLevelNumber() {
		return levelNumber;
	}
}
