package game.animation.data;

import game.config.animation.AnimationConfig;
import javafx.scene.image.Image;

import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.Set;

public interface AnimationData {

    int maxFrameNum(String action);

    default void draw(String action, int frame, double drawX, double drawY) {
        this.draw(action, frame, drawX, drawY, null, null);
    }

    void draw(String action, int frame, double drawX, double drawY, Set<String> images,
              Map<String, String> replaceMap);

    Image buildOneFrameImage(String action, int frameNum,
                             int beginX, int beginY, int imageWidth,
                             int imageHeight, BufferedImage bufferedImage);

    double xSpeed(String action, int frameNum);


    void checkData(String action, int frameNum);

    double height();

    double width();

    double getCrashWidth();

    double getCrashHeight();

    double getCrashX();

    double getCrashY();

    AnimationData customAnimation(String cacheKey, AnimationConfig config
    );


}
