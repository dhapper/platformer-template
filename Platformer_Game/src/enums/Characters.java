package enums;

import utilz.Constants;

import static utilz.Constants.CharacterAnimations.*;

public enum Characters {

	MASK_DUDE(0, Paths.Player.MASK_DUDE),
	NINJA_FROG(1, Paths.Player.NINJA_FROG),
	PINK_MAN(2, Paths.Player.PINK_MAN),
	VIRTUAL_GUY(3, Paths.Player.VIRTUAL_GUY);
	
    private final int id;
    private final String path;

    // Constructor
    Characters(int id, String path) {
        this.id = id;
        this.path = path;
    }

    // Getters
    public int getId() {
        return id;
    }
    
    public String getPath() {
        return path;
    }
	
}
