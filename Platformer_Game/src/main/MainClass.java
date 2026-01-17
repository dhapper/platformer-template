package main;

public class MainClass {
	
	public static void main(String[] args) {
		
		String filepath = "res/game_music/Sketchbook 2025-11-12.wav";
		Audio background= new Audio(filepath);
		background.loop(-20);
		
		new Game();
	}

}
