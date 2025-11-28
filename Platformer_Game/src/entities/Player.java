package entities;

import main.Animation;

import utilz.Constants;
import utilz.Constants.CharacterAnimations.Paths;

import java.awt.Graphics;
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

	public Player(float x, float y) {
		super(x, y);
		
		init();	
	}
	
	private void init() {
		this.width = (int) (SCALE * 16);
		this.height = (int) (SCALE * 16);
		
		initHitbox(x, y, hitboxWidth, hitboxHeight);
		loadAnims();
	}

	public void update() {
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
		
		drawHitbox(g);
	}
	
	public void updatePos() {

	    if (upPressed)    y -= speed;
	    if (downPressed)  y += speed;
	    if (leftPressed)  x -= speed;
	    if (rightPressed) x += speed;
	    
	    
	    hitbox.x = x;
	    hitbox.y = y;
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
