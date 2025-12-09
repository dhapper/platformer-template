package utilz;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import entities.Entity;
import entities.LivingEntity;

public class HelperMethods {
	
	public static boolean CanMoveHere(Rectangle2D.Float futureHitbox, ArrayList<Entity> entities, LivingEntity livingEntity) {
		
		for(Entity entity : entities) {
			if(entity == livingEntity) { continue; }
			if(entity.getHitbox() == null) { continue; }
			if(futureHitbox.intersects(entity.getHitbox())) { return false; }
		}

		return true;
	}

}
