//package game.resourceUtil.animation.data;
//
//import game.config.animation.AnimationConfig;
//import game.resourceUtil.Fragment;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//public class DataByReanim implements AnimationData {
//
//    private int fps;
//
//    Map<String, List<Fragment>> nameDataMap = new HashMap<>();
//
//
//    @Override
//    public int maxFrameNum(String action) {
//        return nameDataMap.get("anim_" + action).size();
//    }
//
//
//    @Override
//    public Iterable<Fragment> fragments(String action, int frame) {
//        return null;
//    }
//
//    @Override
//    public AnimationData customAnimation(String cacheKey, AnimationConfig config) {
//        return null;
//    }
//
//    @Override
//    public double xSpeed(String action, int frameNum) {
//        return 0;
//    }
//
//    @Override
//    public boolean haveCacheImage() {
//        return false;
//    }
//
//    @Override
//    public ImageDto cacheImage(String action, int frame) {
//        return null;
//    }
//
//    @Override
//    public void checkData(String action, int frameNum) {
//
//    }
//
//    @Override
//    public double height() {
//        return 0;
//    }
//
//    @Override
//    public double width() {
//        return 0;
//    }
//
//    @Override
//    public double getCrashWidth() {
//        return 0;
//    }
//
//    @Override
//    public double getCrashHeight() {
//        return 0;
//    }
//
//    @Override
//    public double getBeginX() {
//        return 0;
//    }
//
//    @Override
//    public double getBeginY() {
//        return 0;
//    }
//
//
//}
