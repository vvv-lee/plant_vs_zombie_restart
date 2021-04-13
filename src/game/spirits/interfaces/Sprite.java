package game.spirits.interfaces;

import game.resourceUtil.animation.Animation;
import game.spirits.util.Production;

public interface Sprite extends Draw, Coordinate {




    boolean needRemove();

    Production doActionAndUpdate();

    Animation getAnimation();

    default String nowAction() {
        return getAnimation().nowAction();
    }

    default boolean crash(Sprite sprite) {
        return sprite.getAnimation().crash(getAnimation());
    }


}
