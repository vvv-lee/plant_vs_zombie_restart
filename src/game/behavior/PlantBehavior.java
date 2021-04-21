package game.behavior;

import game.spirits.interfaces.Plant;


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
