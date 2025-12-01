package entities;

import main.Animation;
import main.LevelManager;
import utilz.Constants;
import utilz.Constants.CharacterAnimations.Paths;
import utilz.HelperMethods;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import static utilz.Constants.General.*;

public class Player extends Entity{
	
	private boolean upPressed, downPressed, leftPressed, rightPressed;
	
	private int xDrawOffset = (int) (SCALE * 3);
	private int yDrawOffset = (int) (SCALE * 4);
	private int hitboxWidth = (int) (SCALE * 10);
	private int hitboxHeight = (int) (SCALE * 12);
	
	private ArrayList<Entity> entities;
	
	private Physics physics;
	private Movement movement;
	private AnimationManager animManager;
	
	private boolean invincible = false;
	
	// movement/physics
	private float speed = 2;
	private int jump = -6;
	private float gravity = 0.1f;
	private float maxFallSpeed = 3f;

	public Player(float x, float y) {
		super(x, y);
		
		init();	
	}
	
	private void init() {
		this.width = (int) (SCALE * 16);
		this.height = (int) (SCALE * 16);
		
		initHitbox(x, y, hitboxWidth, hitboxHeight);
		
		physics = new Physics(gravity, maxFallSpeed);
		movement = new Movement(speed, jump, this);
		animManager = new AnimationManager(this);
	}

	public void update() {
		
		physics.update(hitbox, entities);
		
		movement.updatePos(leftPressed, rightPressed);
		
		animManager.update();
		
	}
	
	public void render(Graphics g) {
		Animation anim = animManager.getCurrentAnimation();
		g.drawImage(anim.getCurrentSprite(),
		            (int) (hitbox.x - xDrawOffset),
		            (int) (hitbox.y - yDrawOffset),
		            64, 64, null);

		
		if(LevelManager.SHOW_HITBOXES) { drawHitbox(g); }
	}
	
	public void triggerHit() {
		if(invincible) { return; }
		
		invincible = true;
		animManager.triggerHit();
	}
	
	public void resetDirBools() {
		upPressed = false;
		downPressed = false;
		leftPressed = false;
		rightPressed = false;
		
	}
	
	// getters and setters
	
	public boolean getUpPressed() {
		return upPressed;
	}
	
	public void setUpPressed(boolean value) {
	    upPressed = value;
	}
	
	public boolean getDownPressed() {
		return downPressed;
	}

	public void setDownPressed(boolean value) {
	    downPressed = value;
	}

	public void setLeftPressed(boolean value) {
	    leftPressed = value;
	}
	
	public boolean getLeftPressed() {
		return leftPressed;
	}

	public void setRightPressed(boolean value) {
	    rightPressed = value;
	}
	
	public boolean getRightPressed() {
		return rightPressed;
	}
	
	public ArrayList<Entity> getEntities(){
		return entities;
	}
	
	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
	
	public Physics getPhysics() {
		return physics;
	}
	
	public Movement getMovement() {
		return movement;
	}
	
	public void setInvincible(boolean invincible) {
		this.invincible = invincible;
	}


}
