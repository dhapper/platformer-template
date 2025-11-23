package utilz;

public class Constants {

	
	public static class ResourcePaths {
		public static final String MainCharacters = "/Pixel Adventure Assets/Main Characters/";
	
		
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
