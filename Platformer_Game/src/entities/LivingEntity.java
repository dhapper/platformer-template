package entities;

import static utilz.Constants.General.SCALE;

import java.util.ArrayList;

import enums.Facing;
import graphics.AnimationDefinition;
import graphics.AnimationManager;
import level.LevelManager;
import systems.Movement;
import systems.Physics;

public abstract class LivingEntity extends Entity implements AnimationDefinition{

	protected int xDrawOffset;
	protected int yDrawOffset;
	protected int hitboxWidth;
	protected int hitboxHeight;
	protected int drawSize;	// ???????????
	
	protected int spriteWidth;
	protected int spriteHeight;
	
	// movement/physics
	protected float speed;
	protected float jump;
	protected float doubleJump;
	protected float gravity;
	protected float maxFallSpeed;
	protected Facing facing;
	
	protected Physics physics;
	protected Movement movement;
	protected AnimationManager animManager;
	protected LevelManager levelManager;
	
	protected boolean invincible = false;
	protected boolean hurt = false;

	
	public LivingEntity(float x, float y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	
	protected void initLivingEntity() {
		
		initHitbox(x, y, hitboxWidth, hitboxHeight);
		
		physics = new Physics(gravity, maxFallSpeed, this);
		movement = new Movement(speed, jump, doubleJump, this);
		animManager = new AnimationManager(this);
	}
	
	public void importLevelManager(LevelManager levelManager) {
		this.levelManager = levelManager;
	}
	
	// getters and setters
	
	
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
	
	public int getSpriteWidth() {
		return spriteWidth;
	}
	
	public int getSpriteHeight() {
		return spriteHeight;
	}
	
	public boolean isHurt() {
		return hurt;
	}
	
	public void setHurt(boolean hurt) {
		this.hurt = hurt;
	}

}
