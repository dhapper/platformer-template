package graphics;

import entities.LivingEntity;
import entities.Player;
import enums.AnimState;
import utilz.Constants;
import utilz.Constants.CharacterAnimations.Paths;

public class AnimationManager {

    private Animation[] anims;	// one Animation per AnimState
    private AnimState state = AnimState.IDLE;
    private AnimState prevState = null;

    private int aniTick = 0;
    private int defaultAniSpeed = 10;

    private final LivingEntity livingEntity;

    public AnimationManager(LivingEntity livingEntity) {
        this.livingEntity = livingEntity;
//        loadAnims();
        loadAnimations(livingEntity);
    }
    
    private void loadAnimations(LivingEntity entity) {
        AnimationDefinition def = (AnimationDefinition) entity;

        anims = new Animation[AnimState.values().length];

        for (AnimationConfig cfg : def.getAnimationConfigs()) {
            anims[cfg.state.ordinal()] = new Animation(cfg.path, cfg.frames, cfg.speed, livingEntity.getSpriteWidth(), livingEntity.getSpriteHeight());
        }
    }

    public void updatePlayer() {
        choosePlayerState();
        tickAnimation();
    }
    
    public void updateEnemy() {
    	chooseEnemyState();
    	tickAnimation();
    }

    // return the Animation object (single instance that holds left+right)
    public Animation getCurrentAnimation() {
        return anims[state.ordinal()];
    }
    
    public void setState(AnimState newState) {
    	prevState = state;
    	state = newState;
    }
    
    public void chooseEnemyState() {
       
    	if(livingEntity.isHurt()) {
    		changeState(AnimState.HIT_1);
    		if (!getCurrentAnimation().isAnimationEnded()) return;
    		else livingEntity.setHurt(false);
    	}
    	
        // Default Walk
        changeState(AnimState.WALK);
    }
    
    

    public void choosePlayerState() {
        // HIT lock
        if (state == AnimState.HIT) {
            if (!getCurrentAnimation().isAnimationEnded()) return;
            else livingEntity.setInvincible(false);
        }

        // DOUBLE_JUMP lock (if you still use that logic)
        if (!livingEntity.getPhysics().isOnGround() && livingEntity.getMovement().isDoubleJumpUsed()) {
            if (!getCurrentAnimation().isAnimationEnded()) return;
        }

        // AIRBORNE
        if (!livingEntity.getPhysics().isOnGround()) {
            float velY = livingEntity.getPhysics().getVelY();
            if (velY < 0) changeState(AnimState.JUMP);
            else changeState(AnimState.FALL);
            return;
        }

        // GROUND
        livingEntity.getMovement().setDoubleJumpUsed(false);
        if (livingEntity.getMovement().isMoving()) changeState(AnimState.RUN);
        else changeState(AnimState.IDLE);
    }

    private void tickAnimation() {
        Animation anim = getCurrentAnimation();

        // state change = reset animation
        if (prevState != state) {
            prevState = state;
            aniTick = 0;
            anim.reset();
            return;
        }

        aniTick++;
        if (aniTick >= anim.getSpeed()) {
            aniTick = 0;
            anim.nextFrame();
        }
    }

    private void changeState(AnimState newState) {
        if (state != newState) state = newState;
    }

    public void triggerSingleCycle(AnimState s) {
        changeState(s);
        getCurrentAnimation().reset();
        prevState = null;
        aniTick = 0;
    }

}
