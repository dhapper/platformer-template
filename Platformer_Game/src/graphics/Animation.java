package graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class Animation {

    private BufferedImage spriteSheet;
    private BufferedImage[] sprites;
    private int currentFrame = 0;
    private boolean isFirstFrame = true;
    private int frames;
    private int speed;

    // Load from sprite sheet
    public Animation(String path, int frames, int speed) {
        this.frames = frames;
        this.speed = speed;
        
        try (InputStream is = getClass().getResourceAsStream(path)) {
            this.spriteSheet = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        prepSprites();
    }

    private void prepSprites() {
        sprites = new BufferedImage[frames];
        for (int i = 0; i < frames; i++) {
            sprites[i] = spriteSheet.getSubimage(i * 32, 0, 32, 32);
        }
    }

    public BufferedImage getCurrentSprite() {
        return sprites[currentFrame];
    }

    public void nextFrame() {
        currentFrame++;
        if (currentFrame >= frames) {
            currentFrame = 0;
        }
        isFirstFrame = false;
    }

    public void reset() {
        currentFrame = 0;
        isFirstFrame = true;
    }

    public boolean isAnimationEnded() {
        return !isFirstFrame && currentFrame == frames - 1;
    }
    
    public int getSpeed() {
    	return speed;
    }
}
