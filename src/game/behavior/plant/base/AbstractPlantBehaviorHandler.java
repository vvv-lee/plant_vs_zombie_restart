package game.behavior.plant.base;

import game.behavior.BehaviorHandler;
import game.spirits.util.Production;

public abstract class AbstractPlantBehaviorHandler implements BehaviorHandler {
    @Override
    public abstract boolean canUpdate(String action);

    @Override
    public abstract String updateBehavior(String action);

    @Override
    public void addNewSprites(Production production) {

    }

}
