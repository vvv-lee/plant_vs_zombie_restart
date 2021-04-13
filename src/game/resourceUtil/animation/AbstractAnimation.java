package game.resourceUtil.animation;

import game.main.Game;
import game.resourceUtil.animation.data.AnimationData;
import game.resourceUtil.animation.data.AnimationDrawUtil;
import game.spirits.interfaces.Coordinate;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Affine;

import java.util.Objects;

public abstract class AbstractAnimation implements Animation {

    protected double frameNum = 0;

    protected String nowAction;

    protected Coordinate coordinate;

    protected AnimationData animationData;

    protected double xSpeed = 0;

    protected double angle;

    public AnimationData getAnimationData() {
        return animationData;
    }

    @Override
    public Coordinate getCoordinate() {
        return coordinate;
    }

    protected static int frame(double frameNum) {
        int smallNum = Double.valueOf(frameNum).intValue();
        int maxNum = smallNum + 1;
        if ((maxNum - frameNum) < 0.03) return maxNum;
        return smallNum;
    }

    protected static double utilFrame(double frameNum) {
        int smallNum = Double.valueOf(frameNum).intValue();
        int maxNum = smallNum + 1;
        if ((maxNum - frameNum) < 0.02) return maxNum;
        if ((frameNum - smallNum) < 0.02) return smallNum;
        return frameNum;
    }

    public void newAction(String action) {
        this.nowAction = action;
        this.frameNum = 0;

        getAnimationData().checkData(action, 0);

    }


    public boolean animationOver(String action) {
        return Objects.equals(action, nowAction) && animationOver();
    }

    public boolean animationOver() {
        return frame(frameNum) >= maxFrameNum();
    }

    public int maxFrameNum(String action) {
        return getAnimationData().maxFrameNum(action);
    }

    public int maxFrameNum() {
        return maxFrameNum(nowAction);
    }

    public void nextFrame() {
        nextFrame(1);
    }

    public void nextFrame(double speed) {
        if (animationOver()) frameNum = 0;
        this.xSpeed = buildSpeed(frameNum, speed);
        frameNum = utilFrame(frameNum + speed);

    }

    private double buildSpeed(double frameNum, double speed) {

        double result;
        int finalFrameNum = Double.valueOf(frameNum + speed).intValue();
        double pre = 1 - (frameNum - (int) frameNum);
        if (Math.abs(speed - pre) > 0.01) return 0;

        result = getAnimationData().xSpeed(nowAction, getRealPicFrameNum());
        int beginFrameNum = (int) frameNum + 1;
        while (beginFrameNum < finalFrameNum) {
            result = result + getAnimationData().xSpeed(nowAction, beginFrameNum % maxFrameNum());
            beginFrameNum++;
        }
        return result;


    }


    public int getRealPicFrameNum() {
        return frame(frameNum) % maxFrameNum();
    }


    @Override
    public double getYSpeed() {
        return 0;
    }

    @Override
    public double getXSpeed() {
        double result = this.xSpeed;
        this.xSpeed = 0;
        return result;
    }

    @Override
    public void draw() {
        drawWithOffset(0, 0);
    }

    @Override
    public void drawWithOffset(double offSetX, double offSetY) {
        if (angle != 0) {
            GraphicsContext graphicsContext = Game.getGraphicsContext();
            Affine affine = graphicsContext.getTransform();
            affine.appendRotation(angle,
                    coordinate.getX() + animationData.getCrashWidth() / 2,
                    coordinate.getY()
                            - animationData.getCrashHeight() / 2);
            graphicsContext.setTransform(affine);

        }
        animationData.draw(nowAction,
                getRealPicFrameNum()
                ,coordinate.getX() + offSetX,coordinate.getY() + offSetY);


    }

    @Override
    public boolean preciseFrame(int frameNum) {
        return Math.abs(this.frameNum - frameNum) <= 0.01;
    }

    @Override
    public Animation inheritanceStatus(Animation animation) {
        this.nowAction = animation.nowAction();
        this.frameNum = animation.getPreciseFrameNum();
        return this;
    }

    @Override
    public String nowAction() {
        return nowAction;
    }

    @Override
    public double getPreciseFrameNum() {
        return frameNum;
    }

    @Override
    public void toFrame(int frameNum) {
        this.frameNum = frameNum;
    }

    @Override
    public boolean crash(Animation animation) {
        return xCrash(animation) && yCrash(animation);
    }

    private boolean xCrash(Animation otherAnimation) {
        AnimationData otherData = otherAnimation.getAnimationData();
        Coordinate otherCoordinate = otherAnimation.getCoordinate();
        double beginX = otherData.getBeginX() + otherCoordinate.getX();
        double overX = beginX + otherData.getCrashWidth();
        double thisBeginX = coordinate.getX() + this.animationData.getBeginX();
        double thisOverX = thisBeginX + this.animationData.getCrashWidth();
        if (thisBeginX >= beginX && thisBeginX <= overX) return true;
        return thisOverX >= beginX && thisOverX <= overX;
    }

    private boolean yCrash(Animation otherAnimation) {
        AnimationData otherData = otherAnimation.getAnimationData();
        Coordinate otherCoordinate = otherAnimation.getCoordinate();
        double beginY = otherCoordinate.getY() - otherData.getBeginY() - otherData.getCrashHeight();
        double overY = beginY + otherData.getCrashHeight();
        double thisBeginY = coordinate.getY() - this.animationData.getBeginY() - this.animationData.getCrashHeight();
        double thisOverY = thisBeginY + this.animationData.getCrashHeight();
        if (thisBeginY >= beginY && thisBeginY <= overY) return true;
        return thisOverY >= beginY && thisOverY <= overY;
    }


    @Override
    public void withAngle(double angle) {
        this.angle = angle;
    }

    @Override
    public double centerX() {
        return coordinate.getX() + animationData.getCrashWidth() / 2;
    }

    @Override
    public double centerY() {
        return coordinate.getY() - animationData.getCrashHeight() / 2;
    }

}
