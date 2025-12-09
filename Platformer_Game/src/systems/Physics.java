package systems;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import entities.Entity;
import entities.LivingEntity;
import utilz.HelperMethods;

public class Physics {
	
	private LivingEntity livingEntity;
	private float velY = 0;
	private float gravity;
	private float maxFallSpeed;
	private boolean onGround = false;
	
	public Physics(float gravity, float maxFallSpeed, LivingEntity livingEntity) {
		this.gravity = gravity;
		this.maxFallSpeed = maxFallSpeed;
		this.livingEntity = livingEntity;
	}
	
	public void update(Rectangle2D.Float hitbox, ArrayList<Entity> entities) {
		onGround = checkIfOnGround(hitbox, entities);
		applyGravity();
	}
	
	private void applyGravity() {
	    if (!onGround) {
	        velY += gravity;
	        if (velY > maxFallSpeed)
	            velY = maxFallSpeed;
	    }
	}
	
	private boolean checkIfOnGround(Rectangle2D.Float hitbox, ArrayList<Entity> entities) {
	    // A tiny step downward
	    Rectangle2D.Float check = new Rectangle2D.Float(
	            hitbox.x,
	            hitbox.y + 1,
	            hitbox.width,
	            hitbox.height
	    );

	    return !HelperMethods.CanMoveHere(check, entities, livingEntity);
	}
	
	public float getVelY() {
		return velY;
	}
	
	public void setVelY(float velY) {
		this.velY = velY;
	}
	
	public boolean isOnGround() {
		return onGround;
	}
	
	public void setOnGround(boolean onGround) {
		this.onGround = onGround;
	}
	

}
