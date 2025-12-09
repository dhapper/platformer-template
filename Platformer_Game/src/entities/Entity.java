package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public abstract class Entity {
	
	
	protected float x, y;
	protected int width, height;
	protected Rectangle2D.Float hitbox = null;
	
	public Entity(float x, float y) {
		this.x = x;
		this.y = y;
		
	}
	
	protected void initHitbox(float x, float y, float width, float height) {
		hitbox = new Rectangle2D.Float(x, y, width, height);
	}
	
	public void drawHitbox(Graphics g) {
		g.setColor(Color.red);
		g.drawRect((int) hitbox.x, (int) hitbox.y, (int) hitbox.width, (int) hitbox.height);
	}
	
	public Rectangle2D.Float getHitbox(){
		return hitbox;
	}
	

}
