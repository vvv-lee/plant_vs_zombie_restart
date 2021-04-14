package game.animation;

import game.animation.data.AnimationData;
import game.spirits.interfaces.Coordinate;


public interface Animation {

    void withAngle(double angleNum);

    double centerX();

    double centerY();

    Coordinate getCoordinate();

    boolean crash(Animation animation);

    void toFrame(int frameNum);

    Animation inheritanceStatus(Animation animation);

    double getPreciseFrameNum();

    boolean preciseFrame(int frameNum);

    default boolean preciseLastFrame() {
        return preciseFrame(maxFrameNum() - 1);
    }

    default boolean preciseFirstFrame() {
        return preciseFrame(0);
    }

    void draw();

    void drawWithOffset(double offSetX, double offSetY);

    void newAction(String action);

    boolean animationOver(String action);

    boolean animationOver();

    int maxFrameNum(String action);

    int maxFrameNum();

    default void nextFrame() {
        nextFrame(1);
    }

    void nextFrame(double speed);

    int getRealPicFrameNum();

    double getXSpeed();

    double getYSpeed();

    String nowAction();

    AnimationData getAnimationData();


}
