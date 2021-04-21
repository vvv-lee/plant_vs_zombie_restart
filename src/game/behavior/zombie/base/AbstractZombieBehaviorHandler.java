package game.behavior.zombie.base;

import game.behavior.BehaviorHandler;
import game.spirits.util.Production;

public abstract class AbstractZombieBehaviorHandler implements BehaviorHandler {

    @Override
    public abstract boolean canUpdate(String action);

    @Override
    public abstract String updateBehavior(String action);

    @Override
    public void addNewSprites(Production production) {

    }

}
