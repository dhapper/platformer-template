package utilz;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import entities.Entity;
import entities.LivingEntity;
import enums.Facing;

public class HelperMethods {
	
	public static boolean CanMoveHere(Rectangle2D.Float futureHitbox, ArrayList<Entity> entities, LivingEntity livingEntity) {
		
		for(Entity entity : entities) {
			if(entity == livingEntity) { continue; }
			if(entity.getHitbox() == null) { continue; }
			if(futureHitbox.intersects(entity.getHitbox())) { return false; }
		}

		return true;
	}
	
	public static boolean ShouldTurn(Rectangle2D.Float hitbox, ArrayList<Entity> entities, LivingEntity livingEntity) {
		
		float x = livingEntity.getFacing() == Facing.LEFT ? hitbox.x : hitbox.x + hitbox.width;
		Point2D.Float turnDetection = new Point2D.Float(x, hitbox.y + hitbox.height + 1);
		for(Entity entity : entities) {
			if(entity == livingEntity) { continue; }
			if(entity.getHitbox() == null) { continue; }
			if(entity.getHitbox().contains(turnDetection)) { return false; }
		}
		return true;
	}

}
