package entities;

import static utilz.Constants.CharacterAnimations.Paths.Enemy.*;
import static utilz.Constants.General.SCALE;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import enums.AnimState;
import enums.Facing;
import graphics.Animation;
import graphics.AnimationConfig;
import level.LevelManager;
import systems.EnemyMovement;
import utilz.Constants;

public class Chicken extends LivingEntity{

	public Chicken(float x, float y) {
		super(x, y);
		
		init();
		initLivingEntity();
		enemyMovement = new EnemyMovement(movement);
		
		animManager.setState(AnimState.IDLE);

	}
	
	private void init() {
		endPath = "Chicken";
		pixelSpec = PixelSpec.PX_32_X_34;
		
		spriteWidth = 32;
		spriteHeight = 34;
		
		xDrawOffset = (int) (SCALE * 2);
		yDrawOffset = (int) (SCALE * 9);
		hitboxWidth = (int) (SCALE * 28);
		hitboxHeight = (int) (SCALE * 24);
		
		speed = 0.5f * SCALE;
		jump = -1.5f * SCALE;
		doubleJump = -1f * SCALE;
		gravity = 0.001f * SCALE;
		maxFallSpeed = 0.75f * SCALE;
		
		facing = Facing.LEFT;
		
	}

    @Override
    public AnimationConfig[] getAnimationConfigs() {
        String base = Constants.ResourcePaths.ENEMIES + endPath;

        return new AnimationConfig[]{
            new AnimationConfig(AnimState.IDLE, base + IDLE + pixelSpec, 13, 10),
            new AnimationConfig(AnimState.RUN, base + RUN + pixelSpec, 14, 10),
            new AnimationConfig(AnimState.HIT, base + HIT + pixelSpec, 5, 10)
        };
    }
    
	public void update() {
		physics.update(hitbox, levelManager.getEntities());
		
		enemyMovement.followPlayer();
		
		animManager.updateEnemy();
		
		jumpedOn();
		touchPlayer();
		
	}
	
    public void chooseState() {
        
    	if(hurt) {
    		animManager.changeState(AnimState.HIT);
    		if (!animManager.getCurrentAnimation().isAnimationEnded()) return;
    		else hurt = false;
    	}
    	
    	// if player in vision, run towards
    	float visionWidth = Constants.TileConstants.TERRAIN_TILE_SIZE * 10;
    	vision = new Rectangle2D.Float(
    			hitbox.x + hitbox.width / 2 - visionWidth / 2,
    			hitbox.y,
    			visionWidth,
    			hitboxHeight
    			);
    	
    	if(vision.intersects(player.getHitbox()) && !hitbox.intersects(player.getHitbox())) {
    		playerInVision = true;
    		animManager.changeState(AnimState.RUN);
    		return;
    	} else {
    		playerInVision = false;
    	}
    	
    	// idle on default
    	animManager.changeState(AnimState.IDLE);
    	
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

	    if (LevelManager.SHOW_HITBOXES) {
	    	drawHitbox(g, xLocationOffset);
	    	if(vision != null)
	    		g.drawRect((int) (vision.x - xLocationOffset), (int) vision.y, (int) vision.width, (int) vision.height);
	    }
	}
	
	public void jumpedOn() {
		Player player = levelManager.getPlayer();
		Rectangle2D.Float playerFoot = new Rectangle2D.Float(player.hitbox.x, player.hitbox.y + player.hitbox.height, player.hitbox.width, 1);
		Rectangle2D.Float enemyHead = new Rectangle2D.Float(hitbox.x, hitbox.y - 1, hitbox.width, 1);
		if(playerFoot.intersects(enemyHead)) {
			player.getMovement().enemyBounce();
			animManager.triggerSingleCycle(AnimState.HIT);
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

