package ui;

import java.awt.Rectangle;

public abstract class Button {
	
	protected int xPos, yPos;
	protected Rectangle bounds;
	protected boolean mouseOver, mousePressed;
	
	protected void initBounds(int width, int height) {
		bounds = new Rectangle(xPos, yPos, width, height);
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
