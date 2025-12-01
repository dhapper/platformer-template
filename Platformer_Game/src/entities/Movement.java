package entities;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import utilz.HelperMethods;

public class Movement {
	
	private Player player;
	private Physics physics;
	private float speed;
	private int jump = -6;
	
	public Movement(float speed, int jump, Player player) {
		this.speed = speed;
		this.jump = jump;
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
	        }
	    }

	    if (rightPressed) {
	        Rectangle2D.Float future = new Rectangle2D.Float(hitbox.x + speed, hitbox.y, hitbox.width, hitbox.height);
	        if (HelperMethods.CanMoveHere(future, entities)) {
	            newX += speed;
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
	    }
	}
	
	public boolean isMoving() {
		return player.getUpPressed() || player.getDownPressed() || player.getLeftPressed() || player.getRightPressed();
	}
	
}
