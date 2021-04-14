package game.spirits.recyclable;

import game.config.BaseBuff;
import game.config.animation.Animations;
import game.animation.SingleAnimation;
import game.spirits.interfaces.Coordinate;
import game.spirits.interfaces.Recyclable;

public class Sun extends AbstractRecycle implements Recyclable {

    public Sun(Coordinate coordinate) {
        this(coordinate.getY(), coordinate.getX());
    }

    public Sun(double y, double x) {
        this.xSpeed = 0;
        this.ySpeed = 0;
        this.x = x;
        this.y = y;
        this.setAction("idle");
    }


    @Override
    public int getValue() {
        return BaseBuff.阳光.getNowLevel();
    }


    @Override
    protected double getRecycleY() {
        return 25;
    }

    @Override
    protected double getRecycleX() {
        return 240;
    }


    @Override
    protected void playSoundsWhenRecycle() {

    }

    @Override
    protected SingleAnimation buildAnimation() {
        return new SingleAnimation(this,
                Animations.getByKeyAndFile("Sun", "main"));
    }

    public double getThisActionFrameSpeed() {
        return 1 / 2f;
    }

    @Override
    protected double maxRecycleSpeed() {
        return 25;
    }

    @Override
    protected double radius() {
        return 40;
    }
}
