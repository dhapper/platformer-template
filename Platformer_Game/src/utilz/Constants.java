package utilz;

import java.util.Set;

public class Constants {
	
	
	public static class General {
		public static final float SCALE = 3.0f;
		public static final int GAME_TILES_WIDE = 20;
		public static final int GAME_TILES_HIGH = 12;
		public static final int SCREEN_WIDTH = GAME_TILES_WIDE * TileConstants.TERRAIN_TILE_SIZE;
		public static final int SCREEN_HEIGHT = GAME_TILES_HIGH * TileConstants.TERRAIN_TILE_SIZE;
		public static final int DEFAULT_VOLUME = 2;
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
		
		public static final int DEFAULT_AIR_ID = 5;
		public static final Set<Integer> EMPTY_SPACES = Set.of(DEFAULT_AIR_ID, -1); 
		
		public static final Set<Integer> DROP_THROUGH_TILES = Set.of(
				17, 18, 19,
				17+22, 18+22, 19+22,
				17+44, 18+44, 19+44
				); 
	}
	
	public static class UI {
		
		public static class WHITE_BUTTON {
			public static final int DEFAULT_ICON_BUTTON_WIDTH = 21;
			public static final int DEFAULT_ICON_BUTTON_HEIGHT = 22; 
			public static final int ICON_BUTTON_WIDTH =  (int) (DEFAULT_ICON_BUTTON_WIDTH * General.SCALE);
			public static final int ICON_BUTTON_HEIGHT = (int) (DEFAULT_ICON_BUTTON_HEIGHT * General.SCALE);
		}
		
		public static class RED_BUTTON {
			public static final int DEFAULT_ICON_BUTTON_WIDTH = 15;
			public static final int DEFAULT_ICON_BUTTON_HEIGHT = 16; 
			public static final int ICON_BUTTON_WIDTH =  (int) (DEFAULT_ICON_BUTTON_WIDTH * General.SCALE);
			public static final int ICON_BUTTON_HEIGHT = (int) (DEFAULT_ICON_BUTTON_HEIGHT * General.SCALE);
		}
		
		public static class LEVEL_BUTTON {
			public static final int DEFAULT_LEVEL_BUTTON_WIDTH = 19;
			public static final int DEFAULT_LEVEL_BUTTON_HEIGHT = 17; 
			public static final int LEVEL_BUTTON_WIDTH =  (int) (DEFAULT_LEVEL_BUTTON_WIDTH * General.SCALE);
			public static final int LEVEL_BUTTON_HEIGHT = (int) (DEFAULT_LEVEL_BUTTON_HEIGHT * General.SCALE);
		}
		
		public static class MENU_BUTTON {
			public static final int DEFAULT_MENU_BUTTON_WIDTH = 80;
			public static final int DEFAULT_MENU_BUTTON_HEIGHT = 20; 
			public static final int MENU_BUTTON_WIDTH =  (int) (DEFAULT_MENU_BUTTON_WIDTH * General.SCALE);
			public static final int MENU_BUTTON_HEIGHT = (int) (DEFAULT_MENU_BUTTON_HEIGHT * General.SCALE);
		}
		
		public static class CHARACTER_BUTTON {
			public static final int DEFAULT_CHARACTER_SIZE = 32;
			public static final int CHARACTER_SIZE =  (int) (DEFAULT_CHARACTER_SIZE * General.SCALE);
			public static final int DEFAULT_BORDER_SIZE = 36;
			public static final int BORDER_SIZE =  (int) (DEFAULT_BORDER_SIZE * General.SCALE);
		}
		
	}
	
	
	public static class ResourcePaths {
		public static final String MAIN_CHARACTERS = "/Pixel Adventure Assets/Main Characters/";
		public static final String TILES = "/Pixel Adventure Assets/Terrain/Terrain (16x16).png";
		public static final String BACKGROUND = "/Pixel Adventure Assets/Background/";
		
		public static final String ENEMIES = "/Pixel Adventure Assets/Enemies/";
		
		public static final String MENU_BUTTONS = "/Pixel Adventure Assets/Menu/Buttons/";
		public static final String LEVEL_BUTTONS = "/Pixel Adventure Assets/Menu/Levels/";
		
		public static final String BLACK_TEXT = "/Pixel Adventure Assets/Menu/Text/Text (Black) (8x10).png";
		public static final String WHITE_TEXT = "/Pixel Adventure Assets/Menu/Text/Text (White) (8x10).png";
		
		public static final String UI_SLIDER = "/Custom Assets/slider_ui.png";
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
				
				public static final String MASK_DUDE = "Mask Dude";
				public static final String NINJA_FROG = "Ninja Frog";
				public static final String PINK_MAN = "Pink Man";
				public static final String VIRTUAL_GUY = "Virtual Guy";
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
