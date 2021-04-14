package game.config.animation;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import game.resourceUtil.JsonUtil;
import game.animation.data.*;
import game.animation.data.builder.DataBuilder;
import game.animation.data.builder.DataByJsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * 初始化动画信息
 */
public class Animations {

    private static Logger logger = LoggerFactory.getLogger(Animations.class);

    private final static Gson gson = new Gson();


    public static Map<String, AnimationData> cacheMap = new HashMap<>();

    public static AnimationData getByKeyAndFile(String fileKey, String animationName) {
        return getFromMap(cacheKey(fileKey, animationName));
    }

    public static String cacheKey(String fileKey, String animationName) {
        return fileKey.toLowerCase() + "-" + animationName.toLowerCase();
    }

    public static AnimationData getFromMap(String animationName) {
        AnimationData result = cacheMap.get(animationName.toLowerCase());
        if (result == null) {
            logger.warn("只有" + cacheMap.keySet());
            logger.warn("没有" + animationName);
            throw new RuntimeException("不存在的动画" + animationName);
        }
        return result;

    }


    public static void init() {
        JsonObject configMap = JsonUtil.jsonObjectByPath("json/config/animation.json");
        for (Map.Entry<String, JsonElement> entry : configMap.entrySet()) {
            Director director = gson.fromJson(entry.getValue(), Director.class);
            director.setFileKey(entry.getKey());
            director.customAnimationAndPutIntoCache();
        }
    }

    private static class Director {

        private String fileKey;

        @SerializedName("file")
        private String filePath;

        private double width;

        private double height;

        @SerializedName("animations")

        private Map<String, AnimationConfig> animationConfigMap;

        private Set<String> lessActions;

        private Set<String> lessImages;

        public void setFileKey(String fileKey) {
            this.fileKey = fileKey;
        }

        private static String eName(String file) {
            int i = file.lastIndexOf(".");
            return file.substring(i + 1);
        }

        private static DataBuilder dataBuilder(String file) {
            String eName = eName(file);
            if (eName.equals("json")) {
                return new DataByJsonBuilder(file);
            } else {
                //康明孙
                throw new RuntimeException("现在不支持 原生Reanim文件");
//                return new DataByReanimBuilder(file);
            }
        }

        public void customAnimationAndPutIntoCache() {
            init();
            DataBuilder baseDataBuilder = dataBuilder(this.filePath);

            AnimationData animationData = newBaseData(baseDataBuilder);

            for (Map.Entry<String, AnimationConfig> animationEntry : animationConfigMap.entrySet()) {
                String name = animationEntry.getKey();
                AnimationConfig config = animationEntry.getValue();
                animationData.customAnimation(
                        cacheKey(fileKey, name), config
                );
            }

        }


        private void init() {
            if (this.fileKey == null) {
                int over = filePath.lastIndexOf(".");
                int begin = filePath.lastIndexOf("\\");
                fileKey = filePath.substring(begin + 1, over);
            }
            this.lessActions = new HashSet<>();
            this.lessImages = new HashSet<>();
            for (AnimationConfig animationConfig : animationConfigMap.values()) {
                if (animationConfig.getNeedActions() != null) {
                    lessActions.addAll(animationConfig.getNeedActions());
                }
                if (animationConfig.getFragments() != null) {
                    lessImages.addAll(animationConfig.getFragments());
                }
            }
            if (lessImages.size() == 0) lessImages = null;
            if (lessActions.size() == 0) lessActions = null;
        }

        public AnimationData newBaseData(DataBuilder dataBuilder) {
            return dataBuilder
                    .includeActions(lessActions)
                    .includeImages(lessImages)
                    .height(height)
                    .width(width)
                    .build();
        }
    }


}

