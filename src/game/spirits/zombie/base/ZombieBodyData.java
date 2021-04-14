package game.spirits.zombie.base;

import com.google.common.collect.ImmutableSortedMap;
import game.animation.data.AnimationData;
import game.spirits.interfaces.Protective;

import java.util.*;

public class ZombieBodyData {

    private ImmutableSortedMap<Double, List<String>> statusAnimationMap;

    private Map<String, AnimationData> animationDataMap;

    public ZombieBodyData(ImmutableSortedMap<Double, List<String>> statusAnimationMap,
                          Map<String, AnimationData> animationDataMap
    ) {


        this.statusAnimationMap = statusAnimationMap;
        this.animationDataMap = animationDataMap;
    }

    public List<AnimationData> statusUpdate(double hpValue, Collection<Protective> protectives) {

        List<String> animations = statusAnimationMap.lowerEntry(hpValue).getValue();
        List<AnimationData> result = new ArrayList<>();
        for (Protective protective : protectives) {
            if (protective.clashAnimations() != null)
                animations.removeAll(protective.clashAnimations());
        }
        for (String animation : animations) {
            result.add(animationDataMap.get(animation));
        }
        for (Protective protective : protectives) {
            if (protective.showDataList() != null)
            result.addAll(protective.showDataList());
        }
        return result;
    }


}
