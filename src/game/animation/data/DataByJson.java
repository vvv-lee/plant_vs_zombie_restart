package game.animation.data;

import game.config.animation.AnimationConfig;
import game.config.animation.Animations;
import game.resourceUtil.Fragment;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

public class DataByJson implements AnimationData {

    protected static Logger logger = LoggerFactory.getLogger(DataByJson.class);

    protected String key;

    protected boolean haveCache = false;

    protected Map<String, List<Double>> speedMap;

    protected Map<String, List<ImageDto>> cacheImagMap;

    protected Map<String, List<List<Fragment>>> fragmentMap;

    protected Set<String> showFragmentList;

    protected double crashWidth;

    protected double crashHeight;

    public DataByJson(
            Map<String, List<List<Fragment>>> fragmentMap,
            Map<String, List<Double>> speedMap,
            Set<String> showFragmentList
    ) {
        this(fragmentMap, speedMap, showFragmentList, false);

    }

    protected DataByJson(Map<String, List<List<Fragment>>> fragmentMap,
                         Map<String, List<Double>> speedMap, Set<String> showFragmentList,
                         boolean cacheImage) {
        this.fragmentMap = fragmentMap;
        this.speedMap = speedMap;
        this.showFragmentList = showFragmentList;
        if (cacheImage) {
            buildCacheData();
        }
    }

    public DataByJson buildCacheData() {
//        if (haveCache) return this;
////        buildImageCacheArgs();
//        buildCacheImageMap();
////        this.fragmentMap = null;
////        this.showFragmentList = null;
//        this.haveCache = true;
        return this;

    }

//    private void buildImageCacheArgs() {
//        double minX = 0;
//        double maxX = 0;
//        double minY = 0;
//        double maxY = 0;
//        for (Map.Entry<String, List<List<Fragment>>> entry : fragmentMap.entrySet()) {
//            for (List<Fragment> fragments : entry.getValue()) {
//                for (Fragment fragment : fragments) {
//                    OffSetDto offSetDto = fragment.thisFragmentDto();
//                    minX = Math.min(offSetDto.getMinX(), minX);
//                    minY = Math.min(offSetDto.getMinY(), minY);
//                    maxX = Math.max(offSetDto.getMaxX(), maxX);
//                    maxY = Math.max(offSetDto.getMaxY(), maxY);
//                }
//            }
//        }
//
//        this.imageOffSetX = Double.valueOf(minX).intValue();
//        this.imageOffSetY = Double.valueOf(minY).intValue();
//        this.imgMinWidth = Long.valueOf(Math.round(maxX)).intValue() - imageOffSetX;
//        this.imgMinHeight = Long.valueOf(Math.round(maxY)).intValue() - imageOffSetY;
//
//
//    }

    private void buildCacheImageMap() {
//        boolean needFilter = showFragmentList != null;
//        cacheImagMap = new HashMap<>(fragmentMap.size());
//        for (Map.Entry<String, List<List<Fragment>>> entry : fragmentMap.entrySet()) {
//            List<Image> cacheList = new ArrayList<>(entry.getValue().size());
//            cacheImagMap.put(entry.getKey(), cacheList);
//            for (List<Fragment> fragments : entry.getValue()) {
//                BufferedImage showImage = new BufferedImage(
//                        imgMinWidth, imgMinHeight, BufferedImage.TYPE_INT_ARGB);
//                Graphics2D graphics2D = (Graphics2D) showImage.getGraphics().create();
//                graphics2D.setRenderingHint(
//                        RenderingHints.KEY_INTERPOLATION,
//                        RenderingHints.VALUE_INTERPOLATION_BICUBIC);
//                for (Fragment fragment : fragments) {
//                    if (needFilter) {
//                        if (!showFragmentList.contains(fragment.getImageName())) continue;
//                    }
//                    fragment.drawBufferedImage(graphics2D, -imageOffSetX, -imageOffSetY);
//                }
//                cacheList.add(SwingFXUtils.toFXImage(showImage, null));
//            }
//        }
    }

    @Override
    public AnimationData customAnimation(String cacheKey, AnimationConfig config) {
        return customAnimation(cacheKey, config.getNeedActions(), config.getFragments(), config.getReplace());
    }

    public DataByJson customAnimation(String key,
                                      Set<String> needActions,
                                      Set<String> images,
                                      Map<String, String> replaceMap
    ) {


        DataByJson result = customAnimation(needActions, images, replaceMap);
        if (key != null) Animations.cacheMap.put(key, result);
        return result;
    }

    private DataByJson customAnimation(
            Set<String> needActions,
            Set<String> images,
            Map<String, String> replaceMap) {
        boolean needFilterAction = needActions != null;
        boolean needFilter = images != null;
        boolean needReplace = replaceMap != null;
        int imageSize = images == null ? 0 : images.size();
        Map<String, List<List<Fragment>>> fragmentMap = this.fragmentMap;
        Map<String, List<List<Fragment>>> customFragment = new HashMap<>();
        for (Map.Entry<String, List<List<Fragment>>> entry : fragmentMap.entrySet()) {
            String action = entry.getKey();
            if (needFilterAction && !needActions.contains(action)) continue;
            List<List<Fragment>> value = entry.getValue();
            List<List<Fragment>> frameList = new ArrayList<>(value.size());
            for (List<Fragment> fragments : value) {
                List<Fragment> frame = new ArrayList<>(imageSize);
                for (Fragment fragment : fragments) {
                    if (needFilter) {
                        if (!images.contains(fragment.getImageName())) continue;
                    }
                    String imageName = fragment.getImageName();
                    Fragment newFragment = fragment;
                    if (needReplace && replaceMap.containsKey(imageName)) {
                        String newImage = replaceMap.get(imageName);
                        newFragment = Fragment.newFragmentReplaceImage(fragment, newImage);
                    }
                    frame.add(newFragment);
                }
                frameList.add(frame);
            }
            customFragment.put(action, frameList);
        }
        return new DataByJson(customFragment, speedMap, null).setCrashHeight(crashHeight).setCrashWidth(crashWidth);
    }


    @Override
    public int maxFrameNum(String action) {
        if (haveCache) return cacheImagMap.get(action).size();
        List<List<Fragment>> frameLists = fragmentMap.get(action);
        if (frameLists == null) {
            checkData(action, 0);
        }
        assert frameLists != null;
        return frameLists.size();
    }

    @Override
    public double xSpeed(String action, int frameNum) {
        if (speedMap == null || speedMap.get(action) == null) return 0;
        return speedMap.get(action).get(frameNum);
    }

    @Override
    public void draw(String action, int frame, double drawX, double drawY, Set<String> images, Map<String, String> replaceMap) {
        checkData(action, frame);
        if (haveCache) {
            ImageDto imageDto = cacheImagMap.get(action).get(frame);
            AnimationDrawUtil.drawByImageCache(imageDto, drawX, drawY);
        } else {
            List<Fragment> fragments = fragmentMap.get(action).get(frame);
            AnimationDrawUtil.draw(fragments, drawX, drawY - height(), images, replaceMap);
        }
    }

    private static final Map<RenderingHints.Key, Object> renderingHintMap
            = Collections.singletonMap(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);

    @Override
    public Image buildOneFrameImage(String action, int frameNum,
                                    int beginX, int beginY, int imageWidth, int imageHeight, BufferedImage bufferedImage) {
        if (bufferedImage == null) {
            bufferedImage = AnimationDrawUtil.newBufferedImage(
                    imageWidth
                    , imageHeight, beginX, beginY, width(), height());
        }

        Graphics2D graphics2D = (Graphics2D) bufferedImage.getGraphics().create();
        graphics2D.setRenderingHints(renderingHintMap);
        checkData(action, frameNum);
        List<Fragment> fragments = fragmentMap.get(action).get(frameNum);
        fragments.forEach(i -> i.drawBufferedImage(graphics2D, -beginX, -beginY));
        return SwingFXUtils.toFXImage(bufferedImage, null);


    }

    public DataByJson setCrashWidth(double crashWidth) {
        this.crashWidth = crashWidth;
        return this;
    }

    public DataByJson setCrashHeight(double crashHeight) {
        this.crashHeight = crashHeight;
        return this;
    }


    @Override
    public double height() {
        return crashHeight;
    }

    @Override
    public double width() {
        return crashWidth;
    }

    @Override
    public double getBeginX() {
        return 0;
    }

    @Override
    public double getBeginY() {
        return 0;
    }

    @Override
    public double getCrashWidth() {
        return crashWidth;
    }

    @Override
    public double getCrashHeight() {
        return crashHeight;
    }

    @Override
    public void checkData(String action, int frameNum) {
        String errorMsg = null;
        if (this.fragmentMap == null) {
            errorMsg = "重大异常 动画对象没有加载";
        } else if (!this.fragmentMap.containsKey(action)) {
            errorMsg = "没有" + action + "只有" + this.fragmentMap.keySet();
        } else if (this.fragmentMap.get(action).size() <= frameNum) {
            errorMsg = "帧数" + frameNum + "错误,只有"
                    + this.fragmentMap.get(action).size() + "张图片";
        }
        if (errorMsg != null) {
//            System.out.println(fragmentMap.keySet());
            logger.error(errorMsg);
            throw new RuntimeException("校验错误" + key);
        }
    }

    public DataByJson setKey(String key) {
        System.out.println(key);
        this.key = key;
        return this;
    }

}

