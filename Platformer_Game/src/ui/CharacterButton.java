package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import enums.AnimState;
import enums.Characters;
import graphics.Animation;
import graphics.AnimationConfig;
import utilz.Constants;
import utilz.Constants.CharacterAnimations.Paths;
import utilz.LoadSave;

import static utilz.Constants.UI.CHARACTER_BUTTON.*;

public class CharacterButton extends Button{

	private BufferedImage image;
	private Color bgColour = new Color(255, 255, 255, 50);
	private int yPixelOffsetAnimated = -1;
	private int yPixelOffsetStatic = 3;
	
	private Animation animation;
	private int animTick = 0;
	private AnimationConfig animationConfig;
	
	private boolean active = false;
	
	private Characters character;
	
	private BufferedImage[] borders;
	
//	private int spriteSize = 32;
//	private int borderSize = 36;
//	private int drawBorderSize = (int) (borderSize * Constants.General.SCALE);
	
	public CharacterButton(int xPos, int yPos, Characters character) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.character = character;
		
		width = CHARACTER_SIZE;
		height = CHARACTER_SIZE;
		initBounds(width, height);
		
		loadImage();
		loadAnimation();
		createBorders();
	}
	
	private void loadImage() {
		String path = Constants.ResourcePaths.MAIN_CHARACTERS
				+ character.getPath()
				+ Constants.CharacterAnimations.Paths.Player.IDLE;
		image = LoadSave.ImportImg(path).getSubimage(0, 0, DEFAULT_CHARACTER_SIZE, DEFAULT_CHARACTER_SIZE);
	}
	
	private void loadAnimation() {
		String base = Constants.ResourcePaths.MAIN_CHARACTERS + character.getPath();
		animationConfig = new AnimationConfig(AnimState.RUN, base + Paths.Player.RUN, 12, 10);
		animation = new Animation(
				animationConfig.path,
				animationConfig.frames,
				animationConfig.speed,
				DEFAULT_CHARACTER_SIZE,
				DEFAULT_CHARACTER_SIZE
		);
	}
	
	public void createBorders() {
		borders = new BufferedImage[2];
    	
		borders[0] = new BufferedImage(
				DEFAULT_BORDER_SIZE,
				DEFAULT_BORDER_SIZE,
                BufferedImage.TYPE_INT_ARGB
        );
        
		borders[1] = new BufferedImage(
				DEFAULT_BORDER_SIZE,
				DEFAULT_BORDER_SIZE,
                BufferedImage.TYPE_INT_ARGB
        );
        
        Color Selected = new Color(33, 31, 48);
        Color Unselected = new Color(133, 131, 148);
        
        for (int y = 0; y < DEFAULT_BORDER_SIZE; y++) {
            for (int x = 0; x < DEFAULT_BORDER_SIZE; x++) {
            	
            	boolean isEdge =
                        x == 0 || x == DEFAULT_BORDER_SIZE - 1 ||
                        y == 0 || y == DEFAULT_BORDER_SIZE - 1;
            	
            	if (isEdge) {
            		borders[0].setRGB(x, y, Selected.getRGB());
            		borders[1].setRGB(x, y, Unselected.getRGB());
            	}
            }
        }
	}
	
	public void action() {
		active = true;
	}
	
	public void draw(Graphics g) {	
		
		g.setColor(bgColour);
		g.fillRect(xPos,  yPos, width, height);
		
		if(active)
			g.drawImage(
			        animation.getCurrentSprite(),
			        xPos,
			        yPos - yPixelOffsetAnimated * delta,
			        width,
			        height,
			        null
			);
		else
			g.drawImage(image, xPos, yPos - yPixelOffsetStatic * delta, width, height, null);
		
		g.drawImage(active ? borders[0] : borders[1], xPos - delta, yPos - delta, BORDER_SIZE, BORDER_SIZE, null);
		
	}
	
	public void update() {
		updateAnimation();
	}
	
	public void updateAnimation() {
	    animTick++;

	    if (animTick >= animation.getSpeed()) {
	        animTick = 0;
	        animation.nextFrame();
	    }
	}
	
	// getters and setters
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public Characters getCharacter() {
		return character;
	}
	
}
