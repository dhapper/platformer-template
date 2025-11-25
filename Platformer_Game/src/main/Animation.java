package main;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Animation {
	
	
	private BufferedImage spriteSheet;
	private int frames;
	private BufferedImage sprites[];
	private int currentFrame = 0;
	
	private boolean animationEnded = false;
	private boolean isFirstFrame = true;
	
	public Animation (String path, int frames) {
		
		this.frames = frames;
		
//		String path = "/Pixel Adventure Assets/Main Characters/Mask Dude/Idle (32x32).png";
//		path = "/Pixel Adventure Assets/Main Characters/Mask Dude/Idle (32x32).png";
		InputStream is = getClass().getResourceAsStream(path);
		
		try {
			this.spriteSheet = ImageIO.read(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		prepSprites(frames);
	}
	
	private void prepSprites(int frames) {
		sprites = new BufferedImage[frames];
		for(int i = 0; i < frames; i++)
			sprites[i] = spriteSheet.getSubimage(i * 32, 0, 32, 32);
		
	}
	
//	public BufferedImage[] getSprites() {
//		return sprites;
//	}
	
	public BufferedImage getCurrentSprite() {
		return sprites[currentFrame];
	}
	
	public int getCurrentFrame() {
		return currentFrame;
	}
	
	public boolean isAnimationEnded(){
		if(!isFirstFrame && currentFrame == 0)
			return true;
		return false;
	}
	
	public void nextFrame() {
		currentFrame++;
		if(currentFrame >= frames)
			currentFrame = 0;
		isFirstFrame = false;
	}
	
	public void reset() {
	    currentFrame = 0;
	    isFirstFrame = true;
	}


}
