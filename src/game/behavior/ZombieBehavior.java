package game.behavior;

import game.spirits.interfaces.Plant;
import game.spirits.interfaces.Sprite;
import game.spirits.interfaces.Zombie;

public class ZombieBehavior extends SpriteBehavior {

    private Zombie zombie;

    public ZombieBehavior(Sprite sprite) {
        super(sprite);
    }

    public ZombieBehavior(Sprite sprite, String defaultAction) {
        super(sprite, defaultAction);
    }



}
