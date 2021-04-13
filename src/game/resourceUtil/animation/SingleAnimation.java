package game.resourceUtil.animation;

import game.resourceUtil.animation.data.AnimationData;
import game.spirits.interfaces.Coordinate;

public class SingleAnimation extends AbstractAnimation implements Animation {


//    public SingleAnimation(Coordinate coordinate, String key, String path) {
//        this.coordinate = coordinate;
//        this.animationData = DataByJsonBuilder.newBuilder(key, path).build();
//    }

    public SingleAnimation(Coordinate coordinate, AnimationData animationData) {
        if(animationData==null)throw new RuntimeException("没有动画数据");
        this.coordinate = coordinate;
        this.animationData = animationData;
    }



}
