package graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import utilz.Constants;
import utilz.LoadSave;

public class LevelObjectAnimation {
	
	private BufferedImage[] sprites;
	private int currentFrame = 0;
	private boolean singleLoop;
    private int frames;
    private int speed;
    private int tick = 0, index = 0;
    
    private int x, y, width, height;
    
    private boolean finished = false;
    
    public LevelObjectAnimation(String path, int frames, int speed, boolean singleLoop, int x, int y, int width, int height) {
    	this.x = x;
    	this.y = y;
    	this.width = width;
    	this.height = height;
    	
    	this.speed = speed;
    	this.frames = frames;
    	this.singleLoop = singleLoop;
    	
    	loadSprites(path, frames, width, height);
    	
    }
    
    public void update() {
        if (finished) return; // Skip update if already finished

        tick++;
        if (tick >= speed && speed > 0) {
            tick = 0;
            if (singleLoop && index >= frames - 1) {
                // Mark as finished, don't advance further
                finished = true;
                return;
            }
            index++;
            if (index >= frames) {
                if (singleLoop) {
                    index = frames - 1; // stay at last frame
                    finished = true;    // Mark as finished
                } else {
                    index = 0; // loop back
                }
            }
        }
    }
    
    public void render(Graphics g, int xLocationOffset) {
    	g.drawImage(sprites[index], x - xLocationOffset, y, (int) (width * Constants.General.SCALE), (int) (height * Constants.General.SCALE), null);
    }

	private void loadSprites(String path, int frames, int width, int height) {
		sprites = new BufferedImage[frames];
		BufferedImage sheet = LoadSave.ImportImg(path);
		for(int i = 0; i < frames; i++) {
			sprites[i] = sheet.getSubimage(width * i, 0, width, height);
		}
		
	}
	
	public boolean isFinished() {
	    return finished;
	}
	
	

}
