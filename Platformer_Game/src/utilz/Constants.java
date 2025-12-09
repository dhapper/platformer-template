package utilz;

public class Constants {
	
	
	public static class General {
		public static final float SCALE = 4.0f;
		public static final int GAME_TILES_WIDE = 20;
		public static final int GAME_TILES_HIGH = 12;
		public static final int SCREEN_WIDTH = GAME_TILES_WIDE * TileConstants.TERRAIN_TILE_SIZE;
		public static final int SCREEN_HEIGHT = GAME_TILES_HIGH * TileConstants.TERRAIN_TILE_SIZE;
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
		
		public static final String ENEMIES = "/Pixel Adventure Assets/Enemies/";
	}
	
	
	public static class CharacterAnimations {
		
		public static class Paths {
			public static class Player {
				public static final String IDLE = "/Idle (32x32).png";
				public static final String RUN = "/Run (32x32).png";
				public static final String HIT = "/Hit (32x32).png";
				public static final String JUMP = "/Jump (32x32).png";
				public static final String FALL = "/Fall (32x32).png";
				public static final String DOUBLE_JUMP = "/Double Jump (32x32).png";
				public static final String WALL_JUMP = "/Wall Jump (32x32).png";
			}
			
			public static class Enemy {
					public static final String WALK = "/Walk (36x30).png";
					public static final String IDLE = "/Idle (36x30).png";
					public static final String RUN = "/Run (36x30).png";
					public static final String HIT_1 = "/Hit 1 (36x30).png";
					public static final String HIT_2 = "/Hit 2 (36x30).png";
			}
		}		
		
		
	}
	
	
}
