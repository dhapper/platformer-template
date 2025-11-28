package entities;

import static utilz.Constants.General.*;

public class Tile extends Entity{

	private int id;
	
	public Tile(float x, float y, int id) {
		super(x, y);
		
		this.id = id;
		this.width = (int) (16 * SCALE);
		this.height = (int) (16 * SCALE);
		
		if(id != 5) {
			initHitbox(x, y, width, height);
		}
		
	}
	
	public int getId() {
		return id;
	}

}
