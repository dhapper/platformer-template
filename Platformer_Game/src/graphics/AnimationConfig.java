package graphics;

import enums.AnimState;

public class AnimationConfig {
    public AnimState state;
    public String path;
    public int frames;
    public int speed;

    public AnimationConfig(AnimState state, String path, int frames, int speed) {
        this.state = state;
        this.path = path;
        this.frames = frames;
        this.speed = speed;
    }
}
