package entities;

import main.Animation;
import main.LevelManager;
import utilz.Constants;
import utilz.Constants.CharacterAnimations.Paths;
import utilz.HelperMethods;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import static utilz.Constants.General.*;

public class Player extends Entity{
	
	private Animation[] anims;
	private int currentAnimation = 0;
	private int aniTick, aniSpeed = 20;
	private boolean upPressed, downPressed, leftPressed, rightPressed;
	private boolean isHit = false;

	private int speed = 2;
	
	private int xDrawOffset = (int) (SCALE * 3);
	private int yDrawOffset = (int) (SCALE * 4);
	private int hitboxWidth = (int) (SCALE * 10);
	private int hitboxHeight = (int) (SCALE * 12);
	
	private ArrayList<Entity> entities;
	
//	// Physics
//	private float velY = 0;
//	private float gravity = 0.3f;
//	private float maxFallSpeed = 3f;
//
//	private boolean onGround = false;
	
	private Physics physics;


	public Player(float x, float y) {
		super(x, y);
		
		init();	
	}
	
	private void init() {
		this.width = (int) (SCALE * 16);
		this.height = (int) (SCALE * 16);
		
		initHitbox(x, y, hitboxWidth, hitboxHeight);
		loadAnims();
		
		physics = new Physics();
	}

	public void update() {
		
		physics.update(hitbox, entities);
		
//		onGround = checkIfOnGround();
//		
//		applyGravity();
		
		updatePos();
		
		setAnimation();
		updateAnimationTick();
		
		if (isHit) {
		    if (anims[2].isAnimationEnded()) {
		        isHit = false; // Animation finished
		    }
		}
		
	}
	
	public void render(Graphics g) {
		g.drawImage(anims[currentAnimation].getCurrentSprite(), (int) (hitbox.x - xDrawOffset), (int) (hitbox.y - yDrawOffset), 64, 64, null);
		
		if(LevelManager.SHOW_HITBOXES) { drawHitbox(g); }
	}
	
//	private void applyGravity() {
//	    if (!onGround) {
//	        velY += gravity;
//	        if (velY > maxFallSpeed)
//	            velY = maxFallSpeed;
//	    }
//	}


	public void updatePos() {

	    float newX = hitbox.x;
	    float newY = hitbox.y;

	    // --------------------------
	    // HORIZONTAL MOVEMENT
	    // --------------------------
	    if (leftPressed) {
	        Rectangle2D.Float future = new Rectangle2D.Float(hitbox.x - speed, hitbox.y, hitbox.width, hitbox.height);
	        if (HelperMethods.CanMoveHere(future, entities)) {
	            newX -= speed;
	        }
	    }

	    if (rightPressed) {
	        Rectangle2D.Float future = new Rectangle2D.Float(hitbox.x + speed, hitbox.y, hitbox.width, hitbox.height);
	        if (HelperMethods.CanMoveHere(future, entities)) {
	            newX += speed;
	        }
	    }

	    // --------------------------
	    // VERTICAL MOVEMENT (gravity)
	    // --------------------------

	    // Predict future fall position
	    Rectangle2D.Float futureY =
	            new Rectangle2D.Float(hitbox.x, hitbox.y + physics.getVelY(), hitbox.width, hitbox.height);

	    if (HelperMethods.CanMoveHere(futureY, entities)) {
	        newY += physics.getVelY();
	    } else {
	        // Collision -> stop falling
//	        velY = 0;
//	        onGround = true;
	    	physics.setVelY(0);
	    	physics.setOnGround(true);
	    }

	    // --------------------------
	    // APPLY MOVEMENT
	    // --------------------------
	    hitbox.x = newX;
	    hitbox.y = newY;
	}


	private void updateAnimationTick() {
		aniTick++;
		if(aniTick >= aniSpeed) {
			aniTick = 0;
			anims[currentAnimation].nextFrame();
		}
		
	}
	
	public void setAnimation() {

	    // Hit animation OVERRIDES movement animation
	    if (isHit) {
	        currentAnimation = 2; // HIT
	        return;
	    }

	    boolean moving = upPressed || downPressed || leftPressed || rightPressed;

	    if (moving) {
	        currentAnimation = 1; // RUN
	    } else {
	        currentAnimation = 0; // IDLE
	    }
	}


	
	private void loadAnims() {
		anims = new Animation[3];
		
		String idle = Constants.ResourcePaths.MAIN_CHARACTERS + "Ninja Frog" + Paths.IDLE;
		String run = Constants.ResourcePaths.MAIN_CHARACTERS + "Ninja Frog" + Paths.RUN;
		String hit = Constants.ResourcePaths.MAIN_CHARACTERS + "Ninja Frog" + Paths.HIT;
		
		anims[0] = new Animation(idle, 11);
		anims[1] = new Animation(run, 12);
		anims[2] = new Animation(hit, 5);
		
	}
	
	public void setUpPressed(boolean value) {
	    upPressed = value;
	}

	public void setDownPressed(boolean value) {
	    downPressed = value;
	}

	public void setLeftPressed(boolean value) {
	    leftPressed = value;
	}

	public void setRightPressed(boolean value) {
	    rightPressed = value;
	}
	
	public void jump() {
		System.out.println("JUMP PRESSED");
	    if (physics.isOnGround()) {
	    	System.out.println("ACTUALLY JUMPED");
//	        velY = -10; // strong upward impulse
//	        onGround = false;
	    	physics.setVelY(-10);
	    	physics.setOnGround(false);
	    }
	}
	
//	private boolean checkIfOnGround() {
//	    // A tiny step downward
//	    Rectangle2D.Float check = new Rectangle2D.Float(
//	            hitbox.x,
//	            hitbox.y + 1,
//	            hitbox.width,
//	            hitbox.height
//	    );
//
//	    return !HelperMethods.CanMoveHere(check, entities);
//	}


	
//	public void setHit(boolean hit) {
//	    isHit = hit;
//	}
	
	public void setHit(boolean hit) {
	    if (hit && !isHit) {
	        // Starting hit animation
	        isHit = true;
	        anims[2].reset();  // Reset hit animation to first frame
	    }
	}


	public void resetDirBools() {
		upPressed = false;
		downPressed = false;
		leftPressed = false;
		rightPressed = false;
		
	}
	
	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}


}
