package ui;

import static utilz.Constants.ResourcePaths.MENU_BUTTONS;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import enums.Gamestate;
import enums.Icon;
import utilz.LoadSave;

public class IconButton extends Button{

	private Icon icon;
	private BufferedImage img;
	private int width = 64, height = 64;
	
	public IconButton(int xPos, int yPos, Icon icon) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.icon = icon;
		
		loadImage();
		initBounds(width, height);
	}
	
	private void loadImage() {
		String path = "";
		switch(icon) {
			case PLAY:
				path = MENU_BUTTONS + "Play" + ".png";
				break;
		}
		
		img = LoadSave.ImportImg(path);
	}
	
	public void draw(Graphics g) {
		
		if(mouseOver)
			g.fillRect(xPos, yPos, width, height);
		else
			g.drawImage(img, xPos, yPos, width, height, null);
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
