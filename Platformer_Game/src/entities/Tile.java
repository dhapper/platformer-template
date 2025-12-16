package entities;

import static utilz.Constants.General.*;
import static utilz.Constants.TileConstants.*;

public class Tile extends Entity{

	private int id;
	
	public Tile(float x, float y, int id) {
		super(x, y);
		
		this.id = id;
		this.width = (int) (16 * SCALE);
		this.height = (int) (16 * SCALE);
		
		if(EMPTY_SPACES.contains(id))
			id = DEFAULT_AIR_ID;

		if(id != DEFAULT_AIR_ID)
			initHitbox(x, y, width, height);
		
		if(DROP_THROUGH_TILES.contains(id)) {
			initHitbox(x, y, width, 10);
		}
		
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

}
