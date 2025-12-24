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
import java.awt.geom.Rectangle2D;
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
		yDrawOffset = (int) (SCALE * 4);
		hitboxWidth = (int) (SCALE * 24);
		hitboxHeight = (int) (SCALE * 25);
		
		speed = 0.2f * SCALE;
		jump = -1.5f * SCALE;
		doubleJump = -1f * SCALE;
		gravity = 0.0025f * SCALE;
		maxFallSpeed = 0.75f * SCALE;
		
		facing = Facing.LEFT;
	}

    @Override
    public AnimationConfig[] getAnimationConfigs() {
        String base = Constants.ResourcePaths.ENEMIES + "AngryPig";

        return new AnimationConfig[]{
            new AnimationConfig(AnimState.IDLE, base + IDLE, 9, 10),
            new AnimationConfig(AnimState.RUN, base + RUN, 12, 10),
            new AnimationConfig(AnimState.WALK, base + WALK, 16, 10),
            new AnimationConfig(AnimState.HIT_1, base + HIT_1, 5, 10)
        };
    }
    
	public void update() {
		physics.update(hitbox, levelManager.getEntities());
		
		movement.updateEnemyPos();
		
		animManager.updateEnemy();
		
		jumpedOn();
		touchPlayer();
		
	}
	
	public void render(Graphics g, int xLocationOffset) {
	    Animation anim = animManager.getCurrentAnimation();
	    BufferedImage frame = anim.getCurrentSprite();

	    int drawX = (int)(hitbox.x - xDrawOffset - xLocationOffset);
	    int drawY = (int)(hitbox.y - yDrawOffset);

	    if (getFacing() == Facing.LEFT) {
	    	g.drawImage(frame, drawX, drawY, (int) (spriteWidth*SCALE), (int) (spriteHeight*SCALE), null);
	    } else {
	        g.drawImage(
            frame,
            drawX + (int) (spriteWidth*SCALE),
            drawY,
            -(int) (spriteWidth*SCALE),
            (int) (spriteHeight*SCALE),
            null
        );
	    }

	    if (LevelManager.SHOW_HITBOXES) { drawHitbox(g, xLocationOffset); }
	}
	
	public void jumpedOn() {
		Player player = levelManager.getPlayer();
		Rectangle2D.Float playerFoot = new Rectangle2D.Float(player.hitbox.x, player.hitbox.y + player.hitbox.height, player.hitbox.width, 1);
		Rectangle2D.Float enemyHead = new Rectangle2D.Float(hitbox.x, hitbox.y - 1, hitbox.width, 1);
		if(playerFoot.intersects(enemyHead)) {
			player.getMovement().enemyBounce();
			animManager.triggerSingleCycle(AnimState.HIT_1);
			hurt = true;
		}
	}
	
	public void touchPlayer() {
		Player player = levelManager.getPlayer();
		Rectangle2D.Float playerCollisionZone = new Rectangle2D.Float(player.hitbox.x - 1, player.hitbox.y - 1, player.hitbox.width + 2, player.hitbox.height + 1);
		Rectangle2D.Float enemyCollisionZone = new Rectangle2D.Float(hitbox.x - 1, hitbox.y, hitbox.width + 2, hitbox.height + 1);
		
		if(playerCollisionZone.intersects(enemyCollisionZone))
			player.triggerHit();
	}
	

}
