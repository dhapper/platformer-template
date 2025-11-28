package utilz;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import entities.Entity;

public class HelperMethods {
	
	public static boolean CanMoveHere(Rectangle2D.Float futureHitbox, ArrayList<Entity> entities) {
		
		for(Entity entity : entities) {
			if(entity.getHitbox() == null) { continue; }
			if(futureHitbox.intersects(entity.getHitbox())) { return false; }
		}

		return true;
	}

}
