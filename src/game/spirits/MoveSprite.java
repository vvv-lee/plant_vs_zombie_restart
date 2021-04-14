package game.spirits;

import game.spirits.interfaces.Move;
import game.spirits.util.Production;

public abstract class MoveSprite extends AbstractSprite implements Move {


    protected double xSpeed = 0;
    protected double ySpeed = 0;



    @Override
    protected Production doAction() {
        move();
        return super.doAction();
    }


    protected void setX(double x) {
        this.x = x;
    }

    protected void setY(double y) {
        this.y = y;
    }

    @Override
    public double getXSpeed() {
        return xSpeed;
    }

    @Override
    public double getYSpeed() {
        return ySpeed;
    }

    @Override
    public void setXSpeed(double xSpeed) {
        this.xSpeed = xSpeed;
    }

    @Override
    public void setYSpeed(double ySpeed) {
        this.ySpeed = ySpeed;
    }
}
