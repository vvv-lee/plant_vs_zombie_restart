package game.behavior.plant;

import game.behavior.BehaviorHandler;
import game.main.map.GameMap;
import game.spirits.interfaces.Recyclable;

import java.util.List;

public abstract class ProduceBehaviorHandler implements BehaviorHandler {


    public abstract String updateBehavior(String oldAction, GameMap gameMap);

    public abstract List<Recyclable> produce(String action);


}
