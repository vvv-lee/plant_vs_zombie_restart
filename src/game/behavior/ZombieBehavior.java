package game.behavior;

import game.spirits.interfaces.Sprite;

public class ZombieBehavior extends SpriteBehavior {
    public ZombieBehavior(Sprite sprite) {
        super(sprite);
    }

    public ZombieBehavior(Sprite sprite, String defaultAction) {
        super(sprite, defaultAction);
    }

}
