package main;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Audio {
	
	/*
	 *  0.0f     Full volume
	 * -5.0f     Slightly quieter
	 * -10.0f    Good background music
	 * -20.0f    Very quiet
	 * -80.0f    Off
	 */
	
	private Clip clip;
	private String path;
	private boolean playing = false;

	
	public Audio(String path) {
		this.path = path;
		
		initClip();
	}
	
	public void initClip() {
		try {
			File musicPath = new File(path);
		
			if(musicPath.exists()) {
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
				clip = AudioSystem.getClip();
				clip.open(audioInput);
//				clip.start();
			}else {
				System.out.println("Can't find file");
			}	
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void loop(float volume) {
		if (clip == null) return;
		
		setVolume(volume);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        playing = true;
	}
	
	public void stop() {
	    if (clip == null) return;

	    clip.stop();
	    clip.setFramePosition(0);
	    playing = false;
	}

	
    public void setVolume(float volume) {
        if (clip == null) return;

        FloatControl gain = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gain.setValue(volume);
    }

}
