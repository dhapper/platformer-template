package ui;

import static utilz.Constants.ResourcePaths.MENU_BUTTONS;
import static utilz.Constants.UI.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import enums.Gamestate;
import enums.Icon;
import utilz.Constants;
import utilz.LoadSave;

public class IconButton extends Button{

	private Icon icon;
	private BufferedImage image;
	private int width, height;
//	private int defaultWidth, defaultHeight;
	
	public IconButton(int xPos, int yPos, Icon icon) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.icon = icon;
		
		initDimensions();
		initBounds(width, height);
		
		loadImage();
	}
	
	private void initDimensions() {
		if(icon == Icon.BACK || icon == Icon.CLOSE) {
			width = RED_BUTTON.ICON_BUTTON_WIDTH;
			height = RED_BUTTON.ICON_BUTTON_WIDTH;
		} else {
			width = WHITE_BUTTON.ICON_BUTTON_WIDTH;
			height = WHITE_BUTTON.ICON_BUTTON_WIDTH;
		}
		
	}

	private void loadImage() {
		String path = "";
		
		switch (icon) {
		    case ACHIEVEMENTS -> path = MENU_BUTTONS + "Achievements.png";
		    case BACK         -> path = MENU_BUTTONS + "Back.png";
		    case CLOSE        -> path = MENU_BUTTONS + "Close.png";
		    case LEADERBOARD  -> path = MENU_BUTTONS + "Leaderboard.png";
		    case LEVELS       -> path = MENU_BUTTONS + "Levels.png";
		    case NEXT         -> path = MENU_BUTTONS + "Next.png";
		    case PLAY         -> path = MENU_BUTTONS + "Play.png";
		    case PREVIOUS     -> path = MENU_BUTTONS + "Previous.png";
		    case RESTART      -> path = MENU_BUTTONS + "Restart.png";
		    case SETTINGS     -> path = MENU_BUTTONS + "Settings.png";
		    case VOLUME       -> path = MENU_BUTTONS + "Volume.png";
		    default           -> path = null;
		}
		
		image = LoadSave.ImportImg(path);
	}
	
	public void draw(Graphics g) {
		
		int delta = (int) (1 * Constants.General.SCALE);
		
		if (!mouseOver) {
		    g.drawImage(image, xPos, yPos, width, height, null);
		} else {
		    g.drawImage(image, xPos, yPos + delta, width, height, null);
		}

	
	}
	
	public void update() {

	}
	
	public void action() {
		Gamestate.state = Gamestate.PLAYING;
	}
	
	public void resetBools() {
		mouseOver = false;
		mousePressed = true;
	}
	
}
