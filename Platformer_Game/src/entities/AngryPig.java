package entities;

import enums.AnimState;
import enums.Facing;
import graphics.Animation;
import graphics.AnimationConfig;
import level.LevelManager;
import utilz.Constants;

import static utilz.Constants.CharacterAnimations.Paths.Enemy.*;
import static utilz.Constants.General.SCALE;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class AngryPig extends LivingEntity{

	public AngryPig(float x, float y) {
		super(x, y);
		
		init();
		initLivingEntity();
		
		animManager.setState(AnimState.IDLE);

	}
	
	private void init() {
		spriteWidth = 36;
		spriteHeight = 30;
		
		xDrawOffset = (int) (SCALE * 6);
		yDrawOffset = (int) (SCALE * 6);
		hitboxWidth = (int) (SCALE * 24);
		hitboxHeight = (int) (SCALE * 24);
		
		speed = 0.5f * SCALE;
		jump = -1.5f * SCALE;
		doubleJump = -1f * SCALE;
		gravity = 0.025f * SCALE;
		maxFallSpeed = 0.75f * SCALE;
	}

    @Override
    public AnimationConfig[] getAnimationConfigs() {
        String base = Constants.ResourcePaths.ENEMIES + "AngryPig";
        
        System.out.println(base+IDLE);
        System.out.println(base+RUN);
        System.out.println(base+WALK);

        return new AnimationConfig[]{
            new AnimationConfig(AnimState.IDLE, base + IDLE, 9, 10),
//            new AnimationConfig(AnimState.RUN, base + RUN, 12, 10),
//            new AnimationConfig(AnimState.WALK, base + WALK, 16, 10)
        };
    }
    
	public void update() {
		physics.update(hitbox, entities);
//		movement.updatePos(leftPressed, rightPressed);
		animManager.updateEnemy();
		
	    
//		animManager.setState(AnimState.IDLE);
	}
	
	public void render(Graphics g) {
	    Animation anim = animManager.getCurrentAnimation();
	    BufferedImage frame = anim.getCurrentSprite();

	    int drawX = (int)(hitbox.x - xDrawOffset);
	    int drawY = (int)(hitbox.y - yDrawOffset);

	    if (getFacing() == Facing.LEFT) {
	        g.drawImage(
	            frame,
	            drawX + drawSize,
	            drawY,
	            -drawSize,
	            drawSize,
	            null
	        );
	    } else {
	    	g.drawImage(frame, drawX, drawY, (int) (spriteWidth*SCALE), (int) (spriteHeight*SCALE), null);
	    }

	    if (LevelManager.SHOW_HITBOXES) { drawHitbox(g); }
	}

}
