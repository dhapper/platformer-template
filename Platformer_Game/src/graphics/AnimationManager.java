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

//    public Animation getCurrentAnimation() {
//        return anims[state.ordinal()];
//    }

    public void update() {
        chooseState();
        tickAnimation();
    }

    // return the Animation object (single instance that holds left+right)
    public Animation getCurrentAnimation() {
        return anims[state.ordinal()];
    }

    private void chooseState() {
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

//    private void loadAnims() {
//        anims = new Animation[AnimState.values().length];
//
//        String base = Constants.ResourcePaths.MAIN_CHARACTERS + "Ninja Frog";
//        anims[AnimState.IDLE.ordinal()]       = new Animation(base + Paths.IDLE, 11, defaultAniSpeed);
//        anims[AnimState.RUN.ordinal()]        = new Animation(base + Paths.RUN, 12, defaultAniSpeed);
//        anims[AnimState.JUMP.ordinal()]       = new Animation(base + Paths.JUMP, 1, defaultAniSpeed);
//        anims[AnimState.FALL.ordinal()]       = new Animation(base + Paths.FALL, 1, defaultAniSpeed);
//        anims[AnimState.HIT.ordinal()]        = new Animation(base + Paths.HIT, 5, defaultAniSpeed);
//        anims[AnimState.DOUBLE_JUMP.ordinal()] = new Animation(base + Paths.DOUBLE_JUMP, 6, defaultAniSpeed);
//        anims[AnimState.WALL_JUMP.ordinal()]  = new Animation(base + Paths.WALL_JUMP, 5, defaultAniSpeed);
//    }
}
