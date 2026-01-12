package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Button {
	
	protected int xPos, yPos;
	protected int width, height;
	protected Rectangle bounds;
	protected boolean mouseOver, mousePressed;
	protected int imageIndex;
	
	protected void initBounds(int width, int height) {
		bounds = new Rectangle(xPos, yPos, width, height);
	}
	
	public void draw(Graphics g) {
		// placeholder button graphic
		g.setColor(Color.BLACK);
		g.fillRect(xPos, yPos, width, height);
		g.setColor(Color.WHITE);
		g.drawString("button", xPos, yPos + height/3);
	}
	
	public void update() {
		imageIndex = 0;
		if(mousePressed)
			imageIndex = 1;
	}
	
	public void resetBools() {
		mouseOver = false;
		mousePressed = false;
	}
	
	public void action() {
		System.out.println("Button action not implemented!");
	}
	
	// getters and setters

	public boolean isMouseOver() {
		return mouseOver;
	}

	public boolean isMousePressed() {
		return mousePressed;
	}

	public void setMouseOver(boolean mouseOver) {
		this.mouseOver = mouseOver;
	}

	public void setMousePressed(boolean mousePressed) {
		this.mousePressed = mousePressed;
	}
	
	public Rectangle getBounds() {
		return bounds;
	}

}
