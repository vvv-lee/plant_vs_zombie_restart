package game.resourceUtil.animation.data;

import game.config.animation.AnimationConfig;
import game.resourceUtil.Fragment;
import javafx.scene.image.Image;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.List;
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

    double getBeginX();

    double getBeginY();

    AnimationData customAnimation(String cacheKey, AnimationConfig config
    );


}
