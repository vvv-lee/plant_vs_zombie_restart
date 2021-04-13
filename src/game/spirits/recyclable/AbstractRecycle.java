package game.spirits.recyclable;

import game.spirits.MoveSprite;
import game.spirits.interfaces.Recyclable;
import game.spirits.util.ThrowStatus;

public abstract class AbstractRecycle extends MoveSprite implements Recyclable {

    protected int liveTimer = 0;//存在时间

    protected int maxLiveTime = 500;//最长存在时间

    protected int value = 0;

    protected double multiplier = 1;

    protected boolean isRecycling = false;

    protected ThrowStatus throwStatus;


    @Override
    public boolean isRecycling() {
        return isRecycling;
    }


    @Override
    public boolean mouseOn(double mouseOnY, double mouseOnX) {
        double sqrt = Math.sqrt(Math.abs(mouseOnY - y) * Math.abs(mouseOnY - y) + Math.abs(mouseOnX - x) * Math.abs(mouseOnX - x));
        return sqrt < radius();
    }

    @Override
    public void beginRecycle() {
        if (isRecycling()) return;
        this.isRecycling = true;
        double y = (getRecycleY() - getY());
        double x = (getRecycleX() - getX());
        this.ySpeed = maxRecycleSpeed() * Math.sin(Math.atan2(y, x));
        this.xSpeed = maxRecycleSpeed() * Math.cos(Math.atan2(y, x));
    }

    @Override
    protected void update() {
        throwStatus.update();
        this.liveTimer++;

    }

    @Override
    public Recyclable throwIt(double throwHeight, int throwToTopTime, double xSpeed) {
        throwStatus = new ThrowStatus(throwHeight, throwToTopTime, xSpeed);
        this.xSpeed = 0;
        this.ySpeed = 0;
        return this;
    }

    protected boolean isThrowing() {
        return throwStatus != null && !throwStatus.throwOver() && !isRecycling();
    }

    @Override
    public double getXSpeed() {
        if (isThrowing()) return throwStatus.getXSpeed();
        return super.getXSpeed();
    }

    @Override
    public double getYSpeed() {
        if (isThrowing()) return throwStatus.getYSpeed();
        return super.getYSpeed();
    }

    @Override
    public boolean needRemove() {
        return liveTimer > maxLiveTime || successRecycle();
    }

    @Override
    public boolean successRecycle() {
        if (!isRecycling()) return false;
        return Math.abs(getRecycleY() - getY()) < 10 && Math.abs(getRecycleX() - getX()) < 10;
    }

    protected abstract double maxRecycleSpeed();

    protected abstract double radius();

    protected abstract double getRecycleY();

    protected abstract double getRecycleX();


    protected abstract void playSoundsWhenRecycle();


}