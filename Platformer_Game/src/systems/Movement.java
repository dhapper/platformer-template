package systems;

import static utilz.Constants.General.SCALE;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import entities.Entity;
import entities.LivingEntity;
import entities.Player;
import enums.AnimState;
import enums.Facing;
import graphics.LevelObjectAnimation;
import utilz.HelperMethods;

public class Movement {
	
	private LivingEntity livingEntity;
	private Physics physics;
	private float speed;
	private float jump;
	private float doubleJump;
	
	private boolean doubleJumpUsed = false;      // has player already used double jump this air time?
	private boolean doubleJumpTriggered = false; // was double jump pressed on this tick?

	
	public Movement(float speed, float jump, float doubleJump, LivingEntity livingEntity) {
		this.speed = speed;
		this.jump = jump;
		this.doubleJump = doubleJump;
		this.livingEntity = livingEntity;
		this.physics = livingEntity.getPhysics();
	}
	
	public void updateEnemyPos() {
		Rectangle2D.Float hitbox = livingEntity.getHitbox();
		ArrayList<Entity> entities = livingEntity.getLevelManager().getEntities();
		
	    float newX = hitbox.x;
	    float newY = hitbox.y;
	    
	    // --------------------------
	    // VERTICAL MOVEMENT (gravity)
	    // --------------------------

	    // Predict future fall position
	    Rectangle2D.Float futureY =
	            new Rectangle2D.Float(hitbox.x, hitbox.y + physics.getVelY(), hitbox.width, hitbox.height);

	    if (HelperMethods.CanMoveHere(futureY, entities, livingEntity)) {
	        newY += physics.getVelY();
	    } else {
	        // Collision -> stop falling
	    	physics.setVelY(0);
	    	physics.setOnGround(true);
	    }
	    
	    if(!physics.isOnGround() || livingEntity.isHurt()) {
//	    	livingEntity.getAnimManager().setState(AnimState.IDLE);
	    	hitbox.y = newY;
	    	return;
	    }
	    
	    // --------------------------
	    // HORIZONTAL MOVEMENT
	    // --------------------------
	    
//	    boolean turn = HelperMethods.ShouldTurn(hitbox, entities, livingEntity);
//	    
//	    if(turn && livingEntity.getFacing() == Facing.LEFT) {
//	    	livingEntity.setFacing(Facing.RIGHT);
//	    }else if(turn && livingEntity.getFacing() == Facing.RIGHT) {
//	    	livingEntity.setFacing(Facing.LEFT);
//	    }	
	    
	    Rectangle2D.Float future;
	    if(livingEntity.getFacing() == Facing.LEFT) {
	        future = new Rectangle2D.Float(hitbox.x - speed, hitbox.y, hitbox.width, hitbox.height);
	        if (HelperMethods.CanMoveHere(future, entities, livingEntity))
	            newX -= speed;
	    }else {
	        future = new Rectangle2D.Float(hitbox.x + speed, hitbox.y, hitbox.width, hitbox.height);
	        if (HelperMethods.CanMoveHere(future, entities, livingEntity))
	            newX += speed;
	    }
	    
	    boolean turn = HelperMethods.ShouldTurn(hitbox, entities, livingEntity)
	    		 || !HelperMethods.CanMoveHere(future, livingEntity.getLevelManager().getEnemies(), livingEntity)
	    		 || !HelperMethods.CanMoveHere(future, livingEntity.getLevelManager().getTiles(), livingEntity);
	    
	    if(turn && livingEntity.getFacing() == Facing.LEFT) {
	    	livingEntity.setFacing(Facing.RIGHT);
	    }else if(turn && livingEntity.getFacing() == Facing.RIGHT) {
	    	livingEntity.setFacing(Facing.LEFT);
	    }
	    
	    
//	    if(newX != 0)
//	    	livingEntity.getAnimManager().setState(AnimState.WALK);
//	    else
//	    	livingEntity.getAnimManager().setState(AnimState.IDLE);

	    // --------------------------
	    // APPLY MOVEMENT
	    // --------------------------
	    hitbox.x = newX;
	    hitbox.y = newY;
	}

	public void updatePos(boolean leftPressed, boolean rightPressed) {
		
		Rectangle2D.Float hitbox = livingEntity.getHitbox();
		ArrayList<Entity> entities = livingEntity.getLevelManager().getEntities();

	    float newX = hitbox.x;
	    float newY = hitbox.y;

	    // --------------------------
	    // HORIZONTAL MOVEMENT
	    // --------------------------
	    if (leftPressed) {
	        Rectangle2D.Float future = new Rectangle2D.Float(hitbox.x - speed, hitbox.y, hitbox.width, hitbox.height);
	        if (HelperMethods.CanMoveHere(future, entities, livingEntity)) {
	            newX -= speed;
	            if(!rightPressed)
	            	livingEntity.setFacing(Facing.LEFT);
	        }
	    }

	    if (rightPressed) {
	        Rectangle2D.Float future = new Rectangle2D.Float(hitbox.x + speed, hitbox.y, hitbox.width, hitbox.height);
	        if (HelperMethods.CanMoveHere(future, entities, livingEntity)) {
	            newX += speed;
	            if(!leftPressed)
	            	livingEntity.setFacing(Facing.RIGHT);
	        }
	    }

	    // --------------------------
	    // VERTICAL MOVEMENT (gravity)
	    // --------------------------

	    // Predict future fall position
	    Rectangle2D.Float futureY =
	            new Rectangle2D.Float(hitbox.x, hitbox.y + physics.getVelY(), hitbox.width, hitbox.height);

	    if (HelperMethods.CanMoveHere(futureY, entities, livingEntity)) {
	        newY += physics.getVelY();
	    } else {
	        // Collision -> stop falling
	    	physics.setVelY(0);
	    	physics.setOnGround(true);
	    }

	    // --------------------------
	    // APPLY MOVEMENT
	    // --------------------------
	    hitbox.x = newX;
	    hitbox.y = newY;
	}
	
	public void jump() {
	    if (physics.isOnGround()) {
	    	physics.setVelY(jump); // strong upward impulse
	    	physics.setOnGround(false);
	    }else if(!doubleJumpUsed){
	    	doubleJumpUsed = true;
	    	physics.setVelY(doubleJump);
	    	livingEntity.getAnimManager().triggerSingleCycle(AnimState.DOUBLE_JUMP);
	    	
	        createCloud();
	    }
	}
	
	public void createCloud() {
		// --- CREATE CLOUD EFFECT ---
    	int x = (int) (livingEntity.getHitbox().x - SCALE * 5);
    	int y = (int) (livingEntity.getHitbox().y + SCALE * 5);
    	LevelObjectAnimation cloud = new LevelObjectAnimation("/Custom Assets/clouds.png", 4, 20, true, x, y, 32, 32);
    	livingEntity.getLevelManager().addLevelObject(cloud);
	}
	
	public void enemyBounce() {
		physics.setVelY(doubleJump - (0.25f * SCALE));
		doubleJumpUsed = false;
		
		createCloud();
	}
	
	public boolean isMoving() {
		if(livingEntity instanceof Player) {
			Player player = (Player) livingEntity;
			return player.getUpPressed() || player.getDownPressed() || player.getLeftPressed() || player.getRightPressed();
		}else {
			return false;
		}
	}
	
	public boolean isDoubleJumpUsed() {
		return doubleJumpUsed;
	}
	
	public void setDoubleJumpUsed(boolean doubleJumpUsed) {
		this.doubleJumpUsed = doubleJumpUsed;
	}
	
	public boolean isDoubleJumpTriggered() {
		return doubleJumpTriggered;
	}
	
}
