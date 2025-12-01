package entities;

import main.Animation;
import utilz.Constants;
import utilz.Constants.CharacterAnimations.Paths;

public class AnimationManager {

    private Animation[] anims;
    private AnimState state = AnimState.IDLE;
    private AnimState prevState = null;

    // frame ticking
    private int aniTick = 0;
    private int aniSpeed = 20;

    private final Player player;

    public AnimationManager(Player player) {
        this.player = player;
        loadAnims();
    }

    // call this every frame from Player.update()
    public void update() {
        chooseState();
        tickAnimation();
    }

    // returns the current sprite for rendering
    public Animation getCurrentAnimation() {
        return anims[state.ordinal()];
    }

    public int getCurrentStateIndex() {
        return state.ordinal();
    }

    // ---- internal helpers ----

    // choose the state using clear priorities and locks
    private void chooseState() {

        // 1) If we are currently in HIT and the hit animation has NOT ended, keep it.
        if (state == AnimState.HIT) {
            if (!anims[AnimState.HIT.ordinal()].isAnimationEnded()) {
                // stay in HIT until the animation ends
                return;
            } else {
                // hit finished, clear player's hit flag so future choices are free
                player.setInvincible(false);
                // fall through to next logic to choose new state
            }
        }
        


        // 2) airborne states (JUMP / FALL) - read velocity and grounded status from player
        if (!player.getPhysics().isOnGround()) {
            float velY = player.getPhysics().getVelY();
            if (velY < 0) {
                changeState(AnimState.JUMP);
            } else {
                changeState(AnimState.FALL);
            }
            return;
        }

        // 3) ground movement
        if (player.getMovement().isMoving()) {
            changeState(AnimState.RUN);
        } else {
            changeState(AnimState.IDLE);
        }
    }

    // advances frame (called each update); resets frame when state changes
    private void tickAnimation() {
        // if state changed, reset the animation for the new state
        if (prevState != state) {
            anims[state.ordinal()].reset();
            prevState = state;
            aniTick = 0;
            return; // don't advance the first frame the same tick we changed to it (optional)
        }

        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            anims[state.ordinal()].nextFrame();
        }
    }

    // small helper to change state
    private void changeState(AnimState newState) {
        if (state != newState) {
            state = newState;
        }
    }
    
    // call this to start a hit animation safely from Player
    public void triggerHit() {
        // set state to HIT and reset its animation
        changeState(AnimState.HIT);
        anims[AnimState.HIT.ordinal()].reset();
        // ensure the tick/state-tracking picks up the new state properly
        prevState = null;
        aniTick = 0;
    }


    private void loadAnims() {
        anims = new Animation[AnimState.values().length];

        String idle = Constants.ResourcePaths.MAIN_CHARACTERS + "Ninja Frog" + Paths.IDLE;
        String run = Constants.ResourcePaths.MAIN_CHARACTERS + "Ninja Frog" + Paths.RUN;
        String hit = Constants.ResourcePaths.MAIN_CHARACTERS + "Ninja Frog" + Paths.HIT;
        String jump = Constants.ResourcePaths.MAIN_CHARACTERS + "Ninja Frog" + Paths.JUMP;
        String fall = Constants.ResourcePaths.MAIN_CHARACTERS + "Ninja Frog" + Paths.FALL;

        // Make sure indexes match AnimState order:
        anims[AnimState.IDLE.ordinal()] = new Animation(idle, 11);
        anims[AnimState.RUN.ordinal()]  = new Animation(run, 12);
        anims[AnimState.JUMP.ordinal()] = new Animation(jump, 1); // reuse run/single frame or add proper sprite
        anims[AnimState.FALL.ordinal()] = new Animation(fall, 1); // replace with proper sprites if available
        anims[AnimState.HIT.ordinal()]  = new Animation(hit, 5);
    }

}
