package entities;

import static utilz.Constants.General.SCALE;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import graphics.AnimState;
import graphics.Animation;
import graphics.AnimationManager;
import level.LevelManager;
import systems.Movement;
import systems.Physics;

public class Player extends Entity{
	
	private boolean upPressed, downPressed, leftPressed, rightPressed;
	
	private int xDrawOffset = (int) (SCALE * 3);
	private int yDrawOffset = (int) (SCALE * 4);
	private int hitboxWidth = (int) (SCALE * 10);
	private int hitboxHeight = (int) (SCALE * 12);
	
	private int drawSize = (int) (16 * SCALE);
	
	private ArrayList<Entity> entities;
	
	private Physics physics;
	private Movement movement;
	private AnimationManager animManager;
	
	private boolean invincible = false;
	
	// movement/physics
	private float speed = 0.5f * SCALE;
	private float jump = -1.5f * SCALE;
	private float doubleJump = -1f * SCALE;
	private float gravity = 0.025f * SCALE;
	private float maxFallSpeed = 0.75f * SCALE;
	
	private Facing facing = Facing.RIGHT;
	
	private LevelManager levelManager;

	public Player(float x, float y) {
		super(x, y);
		
		init();	
	}
	
	private void init() {
		this.width = (int) (SCALE * 16);
		this.height = (int) (SCALE * 16);
		
		initHitbox(x, y, hitboxWidth, hitboxHeight);
		
		physics = new Physics(gravity, maxFallSpeed);
		movement = new Movement(speed, jump, doubleJump, this);
		animManager = new AnimationManager(this);
	}

	public void update() {
		
		physics.update(hitbox, entities);
		
		movement.updatePos(leftPressed, rightPressed);
		
		animManager.update();
		
	}
	
	public void render(Graphics g) {
	    Animation anim = animManager.getCurrentAnimation();
	    BufferedImage frame = anim.getCurrentSprite();

	    int drawX = (int)(hitbox.x - xDrawOffset);
	    int drawY = (int)(hitbox.y - yDrawOffset);

	    if (getFacing() == Facing.LEFT) {
	        g.drawImage(
	            frame,
	            drawX + drawSize,
	            drawY,
	            -drawSize,
	            drawSize,
	            null
	        );
	    } else {
	        g.drawImage(frame, drawX, drawY, drawSize, drawSize, null);
	    }

	    if (LevelManager.SHOW_HITBOXES) { drawHitbox(g); }
	}

	
	public void triggerHit() {
		if(invincible) { return; }
		
		invincible = true;
		animManager.triggerSingleCycle(AnimState.HIT);
	}
	
	public void resetDirBools() {
		upPressed = false;
		downPressed = false;
		leftPressed = false;
		rightPressed = false;
		
	}
	
	public void importLevelManager(LevelManager levelManager) {
		this.levelManager = levelManager;
	}
	
	public void respawn() {
		hitbox.x = 100;
		hitbox.y = 100;
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
	
	public AnimationManager getAnimManager() {
		return animManager;
	}
	
	public LevelManager getLevelManager() {
		return levelManager;
	}
	
	public void setInvincible(boolean invincible) {
		this.invincible = invincible;
	}

	public Facing getFacing() {
		return facing;
	}
	
	public void setFacing(Facing facing) {
		this.facing = facing;
	}

}
