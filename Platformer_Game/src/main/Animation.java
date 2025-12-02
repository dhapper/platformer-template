package main;

import java.awt.Graphics2D;
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

    // Load from existing frames (for flipped animations)
    public Animation(BufferedImage[] frames, int speed) {
        this.frames = frames.length;
        this.sprites = frames;
        this.currentFrame = 0;
        this.isFirstFrame = true;
        this.speed = speed;
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

    // Create horizontally flipped version
    public Animation createFlippedSprite() {
        BufferedImage[] flippedFrames = new BufferedImage[frames];
        for (int i = 0; i < frames; i++) {
            BufferedImage frame = sprites[i];
            BufferedImage flipped = new BufferedImage(frame.getWidth(), frame.getHeight(), frame.getType());
            Graphics2D g = flipped.createGraphics();
            g.drawImage(frame, frame.getWidth(), 0, -frame.getWidth(), frame.getHeight(), null); // flip horizontally
            g.dispose();
            flippedFrames[i] = flipped;
        }
        return new Animation(flippedFrames, this.speed);
    }
    
    public int getSpeed() {
    	return speed;
    }
}
