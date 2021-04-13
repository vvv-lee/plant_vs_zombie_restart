package game.resourceUtil.animation;

import game.config.animation.Animations;
import game.resourceUtil.animation.data.AnimationData;
import game.resourceUtil.animation.data.DataByJson;
import game.spirits.interfaces.Coordinate;

import java.util.HashMap;
import java.util.Map;

public class MultiAnimationBuilder {

    Map<String, AnimationData> dataMap = new HashMap<>();

    public static MultiAnimationBuilder builder() {
        return new MultiAnimationBuilder();
    }

    public MultiAnimationBuilder newData(String key, AnimationData animationData) {
        if (animationData == null) {
            throw new RuntimeException();
        }
        dataMap.put(key.toLowerCase(), animationData);
        return this;

    }

    public MultiAnimationBuilder newData(String key) {
        key = key.toLowerCase();
        return newData(key, Animations.getFromMap(key));
    }

    public MultiAnimation build(Coordinate coordinate, String useKey) {
        useKey = useKey.toLowerCase();
        return new MultiAnimation(coordinate,dataMap,useKey);
    }
}
