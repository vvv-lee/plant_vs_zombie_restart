package game.behavior.plant.base;

import game.behavior.plant.BehaviorHandler;
import game.spirits.util.Production;

public abstract class AbstractBaseBehaviorHandler implements BehaviorHandler {
    @Override
    public abstract boolean canUpdate(String action);

    @Override
    public abstract String updateBehavior(String action);

    @Override
    public void addNewSprites(Production production) {

    }

}
