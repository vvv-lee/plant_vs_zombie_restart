package game.spirits;

import game.animation.Animation;
import game.spirits.interfaces.Draw;

public abstract class BaseDraw implements Draw {


    protected Animation animation;



    protected void nextFrame() {
        getAnimation().nextFrame(getThisActionFrameSpeed());

    }


    public void draw() {
        getAnimation().draw();
    }

    protected double getThisActionFrameSpeed() {
        return 1;
    }

    protected boolean animationOver() {
        return getAnimation().animationOver();
    }

    public Animation getAnimation() {
        if (animation == null) animation = buildAnimation();
        return animation;
    }

    protected abstract Animation buildAnimation();


}
