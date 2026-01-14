package ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import graphics.TextWriter;
import graphics.TextWriter.TextColour;
import utilz.Constants;
import utilz.LoadSave;

public class Slider {

	private int xPos, yPos;
	private int notches, currentNotch;
	private BufferedImage[] sprites;
	private int spriteSize = 16;
	private int sliderLength; // including icons
	private int drawSize = Constants.TileConstants.TERRAIN_TILE_SIZE;
	private int minSlider, maxSlider;
	
	private Rectangle2D.Float bounds, sliderBounds;
	
	private boolean boundsPressed = false;
	
	private String labelText;
	private TextWriter textWriter;
	private BufferedImage label;
	private int textScale = 3;
	private int yPosText;
	
	public Slider(int xPos, int yPos, String labelText, int notches, int currentNotch) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.labelText = labelText;
		this.notches = notches;
		this.currentNotch = currentNotch;
		
		// title vars
		textWriter = new TextWriter();
		label = textWriter.GetTextImage(labelText, TextColour.BLACK);
		yPosText = yPos;
		this.yPos += label.getHeight() * textScale;
		yPos += label.getHeight() * textScale;
		
		// slider vars
		sliderLength = notches + 1 + 2 - 1;
		bounds = new Rectangle2D.Float(xPos + currentNotch * drawSize, yPos, drawSize, drawSize);
		minSlider = xPos + drawSize;
		maxSlider = xPos + drawSize * notches;
		sliderBounds = new Rectangle2D.Float(xPos + drawSize, yPos, drawSize * notches, drawSize);
		
		loadImages();
	}

	private void loadImages() {
		BufferedImage sheet = LoadSave.ImportImg(Constants.ResourcePaths.UI_SLIDER);
		sprites = new BufferedImage[6]; // 6 = all sprites
		
		sprites[0] = sheet.getSubimage(0 * spriteSize, 0 * spriteSize, spriteSize, spriteSize);
		sprites[1] = sheet.getSubimage(1 * spriteSize, 0 * spriteSize, spriteSize, spriteSize);
		sprites[2] = sheet.getSubimage(2 * spriteSize, 0 * spriteSize, spriteSize, spriteSize);
		sprites[3] = sheet.getSubimage(3 * spriteSize, 0 * spriteSize, spriteSize, spriteSize);
		
		sprites[4] = sheet.getSubimage(0 * spriteSize, 1 * spriteSize, spriteSize, spriteSize);
		sprites[5] = sheet.getSubimage(1 * spriteSize, 1 * spriteSize, spriteSize, spriteSize);
	}
	
	public void draw(Graphics g) {
		for(int i = 0; i < sliderLength; i++) {
			if(i == 0)						g.drawImage(sprites[5], xPos + i * drawSize, yPos, drawSize, drawSize, null);
			else if(i == 1)					g.drawImage(sprites[0], xPos + i * drawSize, yPos, drawSize, drawSize, null);
			else if(i == sliderLength - 2)	g.drawImage(sprites[2], xPos + i * drawSize, yPos, drawSize, drawSize, null);
			else if(i == sliderLength - 1)	g.drawImage(sprites[4], xPos + i * drawSize, yPos, drawSize, drawSize, null);
			else 							g.drawImage(sprites[1], xPos + i * drawSize, yPos, drawSize, drawSize, null);
		}
		
		g.drawImage(sprites[3], (int) bounds.x, yPos, drawSize, drawSize, null);
		//g.drawRect((int) bounds.x, (int) bounds.y, (int) bounds.width, (int) bounds.height);
		//g.drawRect((int) sliderBounds.x, (int) sliderBounds.y, (int) sliderBounds.width, (int) sliderBounds.height);
		
		g.drawImage(label, xPos, yPosText, label.getWidth() * textScale, label.getHeight() * textScale, null);
		
	}
	
	public void updatePos(float mouseX) {
		bounds.x = mouseX - drawSize / 2;
		
		if(bounds.x < minSlider) bounds.x = minSlider;
		if(bounds.x > maxSlider) bounds.x = maxSlider;
	}
	
	public void centralizeHandler(float mouseX) {
		for(int i = 1; i < notches + 1; i++) {
			if (mouseX >= xPos + drawSize * i && mouseX < xPos + drawSize * (i + 1)) {
				bounds.x = xPos + drawSize * i;
				currentNotch = i;
				break;
			}
		}
	}
	
	// input handlers
	
	public void mousePressed(MouseEvent e) {
		if(sliderBounds.contains(e.getX(), e.getY())) {
			boundsPressed = true;
			updatePos(e.getX());
		}
		
	}

	public void mouseReleased(MouseEvent e) {
		if(boundsPressed)
			centralizeHandler(e.getX());
		boundsPressed = false;
	}
	
	public void mouseDragged(MouseEvent e) {
		if(boundsPressed) {
			updatePos(e.getX());
		}
	}

}
