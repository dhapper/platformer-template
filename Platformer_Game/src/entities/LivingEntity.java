package entities;

import java.awt.geom.Rectangle2D;

import enums.Facing;
import graphics.AnimationDefinition;
import graphics.AnimationManager;
import level.LevelManager;
import systems.EnemyMovement;
import systems.Movement;
import systems.Physics;
import utilz.Constants;

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
	protected EnemyMovement enemyMovement;
	protected AnimationManager animManager;
	protected LevelManager levelManager;
	
	protected boolean invincible = false;
	protected boolean hurt = false;
	
	// enemy vars
	protected Player player;
	protected Rectangle2D.Float vision;
	protected boolean playerInVision;
	protected String endPath, pixelSpec;
	
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
	
	public void setPosByTile(int x, int y){
		hitbox.x = x * Constants.TileConstants.TERRAIN_TILE_SIZE;
		hitbox.y = y * Constants.TileConstants.TERRAIN_TILE_SIZE;
	}
	
	public void chooseState() {
		System.out.println("Choose State Unimplemented: " + getClass().getSimpleName());
	}
	
	public void importPlayer(Player player) {
		this.player = player;
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
	
	public Player getPlayer() {
		return player;
	}
	
	public boolean isPlayerInVision() {
		return playerInVision;
	}

}
