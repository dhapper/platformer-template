package entities;

import static utilz.Constants.General.SCALE;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import main.LevelObjectAnimation;
import utilz.HelperMethods;

public class Movement {
	
	private Player player;
	private Physics physics;
	private float speed;
	private float jump;
	private float doubleJump;
	
	private boolean doubleJumpUsed = false;      // has player already used double jump this air time?
	private boolean doubleJumpTriggered = false; // was double jump pressed on this tick?

	
	public Movement(float speed, float jump, float doubleJump, Player player) {
		this.speed = speed;
		this.jump = jump;
		this.doubleJump = doubleJump;
		this.player = player;
		this.physics = player.getPhysics();
	}

	public void updatePos(boolean leftPressed, boolean rightPressed) {
		
		Rectangle2D.Float hitbox = player.getHitbox();
		ArrayList<Entity> entities = player.getEntities();

	    float newX = hitbox.x;
	    float newY = hitbox.y;

	    // --------------------------
	    // HORIZONTAL MOVEMENT
	    // --------------------------
	    if (leftPressed) {
	        Rectangle2D.Float future = new Rectangle2D.Float(hitbox.x - speed, hitbox.y, hitbox.width, hitbox.height);
	        if (HelperMethods.CanMoveHere(future, entities)) {
	            newX -= speed;
	            if(!rightPressed)
	            	player.setFacing(Facing.LEFT);
	        }
	    }

	    if (rightPressed) {
	        Rectangle2D.Float future = new Rectangle2D.Float(hitbox.x + speed, hitbox.y, hitbox.width, hitbox.height);
	        if (HelperMethods.CanMoveHere(future, entities)) {
	            newX += speed;
	            if(!leftPressed)
	            	player.setFacing(Facing.RIGHT);
	        }
	    }

	    // --------------------------
	    // VERTICAL MOVEMENT (gravity)
	    // --------------------------

	    // Predict future fall position
	    Rectangle2D.Float futureY =
	            new Rectangle2D.Float(hitbox.x, hitbox.y + physics.getVelY(), hitbox.width, hitbox.height);

	    if (HelperMethods.CanMoveHere(futureY, entities)) {
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
	    	player.getAnimManager().triggerSingleCycle(AnimState.DOUBLE_JUMP);
	    	
//	        // --- CREATE CLOUD EFFECT ---
	    	int x = (int) (player.getHitbox().x - SCALE * 10);
	    	int y = (int) (player.getHitbox().y - SCALE * 10);
	    	LevelObjectAnimation cloud = new LevelObjectAnimation("/Custom Assets/clouds.png", 4, 20, true, x, y, 32, 32);
	    	player.getLevelManager().addLevelObject(cloud);
	    }
	}
	
	public boolean isMoving() {
		return player.getUpPressed() || player.getDownPressed() || player.getLeftPressed() || player.getRightPressed();
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
