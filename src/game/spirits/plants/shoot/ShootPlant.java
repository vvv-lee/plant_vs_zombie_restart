package game.spirits.plants.shoot;

import game.main.map.MapLawn;
import game.spirits.plants.AbstractPlant;
import game.behavior.plant.shoot.ShootBehaviorHandler;

public abstract class ShootPlant extends AbstractPlant {
    public ShootPlant(MapLawn mapLawn) {
        super(mapLawn);
        addShootBehavior(shootBehavior());
    }

    protected abstract ShootBehaviorHandler shootBehavior();

}
