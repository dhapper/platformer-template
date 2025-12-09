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

//	protected int xDrawOffset = (int) (SCALE * 3);
//	protected int yDrawOffset = (int) (SCALE * 4);
//	protected int hitboxWidth = (int) (SCALE * 10);
//	protected int hitboxHeight = (int) (SCALE * 12);
//
//	protected int drawSize = (int) (16 * SCALE);
//	
//	// movement/physics
//	protected float speed = 0.5f * SCALE;
//	protected float jump = -1.5f * SCALE;
//	protected float doubleJump = -1f * SCALE;
//	protected float gravity = 0.025f * SCALE;
//	protected float maxFallSpeed = 0.75f * SCALE;

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
	protected Facing facing = Facing.RIGHT;
	
	protected ArrayList<Entity> entities;
	protected Physics physics;
	protected Movement movement;
	protected AnimationManager animManager;
	protected LevelManager levelManager;
	
	protected boolean invincible = false;

	
	public LivingEntity(float x, float y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	
	protected void initLivingEntity() {
//		this.width = (int) (SCALE * 16);
//		this.height = (int) (SCALE * 16);
		
		initHitbox(x, y, hitboxWidth, hitboxHeight);
		
		physics = new Physics(gravity, maxFallSpeed);
		movement = new Movement(speed, jump, doubleJump, this);
		animManager = new AnimationManager(this);
	}
	
	// getters and setters
	
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
	
	public int getSpriteWidth() {
		return spriteWidth;
	}
	
	public int getSpriteHeight() {
		return spriteHeight;
	}

}
