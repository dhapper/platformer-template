package main;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

public class Test {

	public static void main(String[] args) {
//		/Platformer_Game/res/game_music/Sketchbook 2025-11-12.wav
		String filepath = "res/game_music/Sketchbook 2025-11-12.wav";
		PlayMusic(filepath);
		JOptionPane.showMessageDialog(null, "stop");
	}
	
	public static void PlayMusic(String path) {
		try {
			File musicPath = new File(path);
			
			if(musicPath.exists()) {
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
				Clip clip = AudioSystem.getClip();
				clip.open(audioInput);
				clip.start();
			}else {
				System.out.println("Can't find file");
			}
			
		} catch(Exception e) {
			System.out.println(e);
		}
		
	}
}
