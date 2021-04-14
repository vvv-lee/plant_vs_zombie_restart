package game.animation.data;

import game.config.animation.AnimationConfig;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import java.awt.image.BufferedImage;
import java.util.*;

public class MultiAnimationData implements AnimationData {


    private LinkedHashSet<AnimationData> dataList;

    private AnimationData mainData;

    public MultiAnimationData(AnimationData mainData) {
        this.mainData = mainData;
        dataList = new LinkedHashSet<>();
    }

    public MultiAnimationData newData(AnimationData animationData) {
        dataList.add(animationData);
        return this;
    }

    @Override
    public int maxFrameNum(String action) {
        return mainData.maxFrameNum(action);
    }

    @Override
    public void draw(String action, int frame, double drawX, double drawY, Set<String> images, Map<String, String> replaceMap) {
        mainData.draw(action, frame, drawX, drawY, images, replaceMap);
        for (AnimationData animationData : dataList) {
            animationData.draw(action, frame, drawX, drawY, images, replaceMap);
        }
    }

    @Override
    public void checkData(String action, int frameNum) {
        mainData.checkData(action, frameNum);

    }

    @Override
    public Image buildOneFrameImage(String action, int frameNum,
                                    int beginX, int beginY,
                                    int imageWidth, int imageHeight, BufferedImage bufferedImage) {

        if (bufferedImage == null) {
            bufferedImage = AnimationDrawUtil.newBufferedImage(
                    imageWidth
                    , imageHeight, beginX, beginY, width(), height());
        }
        for (AnimationData animationData : dataList) {
            animationData.buildOneFrameImage(action, frameNum, beginX, beginY, imageWidth, imageHeight, bufferedImage);
        }

        return SwingFXUtils.toFXImage(bufferedImage, null);
    }

    @Override
    public double xSpeed(String action, int frameNum) {
        return mainData.xSpeed(action, frameNum);
    }

    @Override
    public double height() {
        return mainData.height();
    }

    @Override
    public double width() {
        return mainData.width();
    }

    @Override
    public double getCrashWidth() {
        return mainData.getCrashWidth();
    }

    @Override
    public double getCrashHeight() {
        return mainData.getCrashHeight();
    }

    @Override
    public double getCrashX() {
        return mainData.getCrashX();
    }

    @Override
    public double getCrashY() {
        return mainData.getCrashY();
    }

    @Override
    public AnimationData customAnimation(String cacheKey, AnimationConfig config) {
        AnimationData animationData = mainData.customAnimation(cacheKey, config);
        MultiAnimationData result = new MultiAnimationData(animationData);
        for (AnimationData data : dataList) {
            result.newData(data.customAnimation(cacheKey, config));
        }
        return result;
    }

    public void clear(AnimationData mainData) {
        this.mainData = mainData;
        this.clearOther();

    }

    public void clearOther() {
        this.dataList = new LinkedHashSet<>();

    }
}
