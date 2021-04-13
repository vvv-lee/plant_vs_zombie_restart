package game.behavior;

import game.behavior.plant.BehaviorHandler;
import game.spirits.interfaces.Plant;
import game.spirits.util.Production;

import java.util.ArrayList;
import java.util.List;


public class PlantBehavior extends SpriteBehavior {

    private Plant plant;

    public PlantBehavior(Plant plant) {
        this(plant, "idle");
    }

    public PlantBehavior(Plant plant, String defaultAction) {
        super(plant, defaultAction);
        this.plant = plant;
        this.defaultAction = defaultAction;
    }



}
