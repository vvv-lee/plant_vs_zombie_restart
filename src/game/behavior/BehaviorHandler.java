package game.behavior;

import game.spirits.util.Production;

public interface BehaviorHandler {

    boolean canUpdate(String action);

    String updateBehavior(String action);

    void addNewSprites(Production production);

}
