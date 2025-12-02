package entities;

import main.Animation;
import utilz.Constants;
import utilz.Constants.CharacterAnimations.Paths;

public class AnimationManager {

    private Animation[] anims;                // one Animation per AnimState
    private AnimState state = AnimState.IDLE;
    private AnimState prevState = null;

    private int aniTick = 0;
    private int defaultAniSpeed = 10;

    private final Player player;

    public AnimationManager(Player player) {
        this.player = player;
        loadAnims();
    }

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
            else player.setInvincible(false);
        }

        // DOUBLE_JUMP lock (if you still use that logic)
        if (!player.getPhysics().isOnGround() && player.getMovement().isDoubleJumpUsed()) {
            if (!getCurrentAnimation().isAnimationEnded()) return;
        }

        // AIRBORNE
        if (!player.getPhysics().isOnGround()) {
            float velY = player.getPhysics().getVelY();
            if (velY < 0) changeState(AnimState.JUMP);
            else changeState(AnimState.FALL);
            return;
        }

        // GROUND
        player.getMovement().setDoubleJumpUsed(false);
        if (player.getMovement().isMoving()) changeState(AnimState.RUN);
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

    private void loadAnims() {
        anims = new Animation[AnimState.values().length];

        String base = Constants.ResourcePaths.MAIN_CHARACTERS + "Ninja Frog";
        anims[AnimState.IDLE.ordinal()]       = new Animation(base + Paths.IDLE, 11, defaultAniSpeed);
        anims[AnimState.RUN.ordinal()]        = new Animation(base + Paths.RUN, 12, defaultAniSpeed);
        anims[AnimState.JUMP.ordinal()]       = new Animation(base + Paths.JUMP, 1, defaultAniSpeed);
        anims[AnimState.FALL.ordinal()]       = new Animation(base + Paths.FALL, 1, defaultAniSpeed);
        anims[AnimState.HIT.ordinal()]        = new Animation(base + Paths.HIT, 5, defaultAniSpeed);
        anims[AnimState.DOUBLE_JUMP.ordinal()] = new Animation(base + Paths.DOUBLE_JUMP, 6, defaultAniSpeed);
        anims[AnimState.WALL_JUMP.ordinal()]  = new Animation(base + Paths.WALL_JUMP, 5, defaultAniSpeed);
    }
}
