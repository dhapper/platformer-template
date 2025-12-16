package entities;

import static utilz.Constants.General.SCALE;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import enums.AnimState;
import enums.Facing;
import graphics.Animation;
import graphics.AnimationConfig;
import graphics.AnimationDefinition;
import graphics.AnimationManager;
import graphics.ImageModifier;
import level.LevelManager;
import systems.Movement;
import systems.Physics;
import utilz.Constants;
import utilz.Constants.CharacterAnimations.Paths;

public class Player extends LivingEntity {
	
	private boolean upPressed, downPressed, leftPressed, rightPressed;
	
	private boolean invincible = false;
	private long invincibleEndTime = 0;   // timestamp when invincibility ends


	public Player(float x, float y) {
		super(x, y);
		
		init();
		initLivingEntity();
	}
	
	private void init() {
		spriteWidth = 32;
		spriteHeight = 32;
		
		xDrawOffset = (int) (SCALE * 6);
		yDrawOffset = (int) (SCALE * 8);
		hitboxWidth = (int) (SCALE * 20);
		hitboxHeight = (int) (SCALE * 23);
		
		speed = 0.5f * SCALE;
		jump = -1.5f * SCALE;
		doubleJump = -1f * SCALE;
		gravity = 0.025f * SCALE;
		maxFallSpeed = 0.75f * SCALE;
		
		facing = Facing.RIGHT;
	}
	
    @Override
    public AnimationConfig[] getAnimationConfigs() {
        String base = Constants.ResourcePaths.MAIN_CHARACTERS + "Ninja Frog";

        return new AnimationConfig[]{
            new AnimationConfig(AnimState.IDLE, base + Paths.Player.IDLE, 11, 10),
            new AnimationConfig(AnimState.RUN, base + Paths.Player.RUN, 12, 10),
            new AnimationConfig(AnimState.JUMP, base + Paths.Player.JUMP, 1, 10),
            new AnimationConfig(AnimState.FALL, base + Paths.Player.FALL, 1, 10),
            new AnimationConfig(AnimState.HIT, base + Paths.Player.HIT, 5, 10),
            new AnimationConfig(AnimState.DOUBLE_JUMP, base + Paths.Player.DOUBLE_JUMP, 6, 10),
            new AnimationConfig(AnimState.WALL_JUMP, base + Paths.Player.WALL_JUMP, 5, 10)
        };
    }

	public void update() {
		
		physics.update(hitbox, levelManager.getEntities());
		
		movement.updatePos(leftPressed, rightPressed);
		
		if (invincible && System.currentTimeMillis() >= invincibleEndTime) {
		    invincible = false;
		}

		
		animManager.updatePlayer();
		
		
		
	}
	
	public void render(Graphics g, int xLocationOffset) {
	    Animation anim = animManager.getCurrentAnimation();
	    BufferedImage frame = anim.getCurrentSprite();
	    
	    if(invincible)
	    	frame = ImageModifier.MakeImageTransparent(frame, 0.5f);

	    int drawX = (int)(hitbox.x - xDrawOffset - xLocationOffset);
	    int drawY = (int)(hitbox.y - yDrawOffset);

	    if (getFacing() == Facing.LEFT) {
	        g.drawImage(
	            frame,
	            drawX + (int) (spriteWidth*SCALE),
	            drawY,
	            -(int) (spriteWidth*SCALE),
	            (int) (spriteHeight*SCALE),
	            null
	        );
	    } else {
	        g.drawImage(frame, drawX, drawY, (int) (spriteWidth*SCALE), (int) (spriteHeight*SCALE), null);
	    }

	    if (LevelManager.SHOW_HITBOXES) { drawHitbox(g, xLocationOffset); }
	}

	public void triggerHit() {
	    if (invincible)
	        return;

	    invincible = true;
	    invincibleEndTime = System.currentTimeMillis() + 1000; // 1 second

	    animManager.triggerSingleCycle(AnimState.HIT);
	}

	
	public void resetDirBools() {
		upPressed = false;
		downPressed = false;
		leftPressed = false;
		rightPressed = false;
		
	}
	
	public void respawn() {
		hitbox.x = 4 * Constants.TileConstants.TERRAIN_TILE_SIZE;
		hitbox.y = 4 * Constants.TileConstants.TERRAIN_TILE_SIZE;
	}
	
	// getters and setters
	
	public boolean getUpPressed() {
		return upPressed;
	}
	
	public void setUpPressed(boolean value) {
	    upPressed = value;
	}
	
	public boolean getDownPressed() {
		return downPressed;
	}

	public void setDownPressed(boolean value) {
	    downPressed = value;
	}

	public void setLeftPressed(boolean value) {
	    leftPressed = value;
	}
	
	public boolean getLeftPressed() {
		return leftPressed;
	}

	public void setRightPressed(boolean value) {
	    rightPressed = value;
	}
	
	public boolean getRightPressed() {
		return rightPressed;
	}
	
	public boolean isInvincible() {
		return invincible;
	}

}
