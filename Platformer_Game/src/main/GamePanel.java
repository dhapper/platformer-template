package main;

import java.awt.Graphics;

import javax.swing.JPanel;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

public class GamePanel extends JPanel{

	private MouseInputs mouseInputs;
	private float xDelta = 100, yDelta = 100;
	private float xDir = 2f, yDir = 2f;
	
	public GamePanel() {
		
		// adding input listeners
		mouseInputs = new MouseInputs(this);
		addKeyListener(new KeyboardInputs(this));
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
	}
	
	public void changeXDelta(int value) {
		this.xDelta += value;
		repaint();
	}
	
	public void changeYDelta(int value) {
		this.yDelta += value;
		repaint();
	}
	
	public void setRectPos(int x, int y) {
		this.xDelta = x;
		this.yDelta = y;
		repaint();
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		updateRectangle();
		g.fillRect((int) xDelta, (int) yDelta, 100, 100);
		
	
	}

	private void updateRectangle() {
		xDelta += xDir;
		if(xDelta > 600 || xDelta < 0)
			xDir*=-1;
		yDelta += yDir;
		if(yDelta > 400 || yDelta < 0)
			yDir*=-1;
		
	}
	
}
