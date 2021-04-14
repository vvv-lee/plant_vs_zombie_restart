package game.animation;

import game.animation.data.AnimationData;
import game.animation.data.DataByJson;
import game.animation.data.MultiAnimationData;
import game.spirits.interfaces.Coordinate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.Map;


public class MultiAnimation extends AbstractAnimation {

    private static Logger logger = LoggerFactory.getLogger(DataByJson.class);

    private Map<String, AnimationData> animationDataMap;

    private MultiAnimationData multiAnimationData;

    public MultiAnimation(Coordinate coordinate,
                          Map<String, AnimationData> animationDataMap,
                          String... keys
    ) {

        this.coordinate = coordinate;
        this.animationDataMap = animationDataMap;
        this.multiAnimationData = new MultiAnimationData(animationDataMap.values().iterator().next());
        this.animationData = this.multiAnimationData;
        useByKey(keys);
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }


    public void useByKey(String... keys) {
        LinkedList<AnimationData> linkedList = new LinkedList<>();
        for (String key : keys) {
            key=key.toLowerCase();
            if (!this.animationDataMap.containsKey(key)) {
                logger.error(String.valueOf(animationDataMap.keySet()));
                throw new RuntimeException("空的 key" + key);
            }
            linkedList.add(animationDataMap.get(key));

        }
        AnimationData newMain = linkedList.removeFirst();
        multiAnimationData.clear(newMain);
        for (AnimationData data : linkedList) {
            multiAnimationData.newData(data);
        }
    }

}
