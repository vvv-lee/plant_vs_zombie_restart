package game.spirits.recyclable;


import game.config.animation.Animations;
import game.animation.Animation;
import game.animation.SingleAnimation;

public class CoinSilver extends Money {
    public CoinSilver(double y, double x) {
        this.xSpeed = 0;
        this.ySpeed = 0;
        this.x = x;
        this.y = y;
        this.setAction("idle");
    }

    @Override
    protected double getThisActionFrameSpeed() {
        return 0.25;
    }

    @Override
    protected double maxRecycleSpeed() {
        return 10;
    }

    @Override
    protected double radius() {
        return 10;
    }

    @Override
    protected void playSoundsWhenRecycle() {

    }

    @Override
    protected Animation buildAnimation() {

        return new SingleAnimation(this,
                Animations.getByKeyAndFile("CoinSilver", "main"));
    }

    @Override
    public int getValue() {
        return 0;
    }
}
