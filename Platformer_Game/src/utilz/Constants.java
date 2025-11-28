package utilz;

public class Constants {
	
	
	public static class General {
		public static final float SCALE = 4.0f;
	}
	
	public static class Directions {
		public static final int UP = 0;
		public static final int DOWN = 1;
		public static final int LEFT = 2;
		public static final int RIGHT = 3;
	}
	
	public static class TileConstants {
		public static final int DEFAULT_TERRAIN_TILE_SIZE = 16;
		public static final int TERRAIN_TILE_SIZE = (int) (DEFAULT_TERRAIN_TILE_SIZE * General.SCALE);
	}
	
	
	public static class ResourcePaths {
		public static final String MAIN_CHARACTERS = "/Pixel Adventure Assets/Main Characters/";
		public static final String TILES = "/Pixel Adventure Assets/Terrain/Terrain (16x16).png";
		public static final String BACKGROUND = "/Pixel Adventure Assets/Background/";
		
	}
	
	
	public static class CharacterAnimations {
		
		public static class Paths{
			public static final String IDLE = "/Idle (32x32).png";
			public static final String RUN = "/Run (32x32).png";
			public static final String HIT = "/Hit (32x32).png";
		}
		
		public static class Index {
			public static final int IDLE = 0;
			public static final int RUN = 1;
			public static final int HIT = 2;
		}
		
		public static int GetFrames(int player_action) {
			switch(player_action) {
			case Index.IDLE:
				return 11;
			case Index.RUN:
				return 12;
			case Index.HIT:
				return 5;
			default:
				return 1;
			}
		}
	}
	
	
}
