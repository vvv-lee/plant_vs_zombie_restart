package game.spirits.plants.shoot.peaShooter;

import game.main.map.MapLawn;
import game.behavior.plant.shoot.PSShootBehavior;


public class PeaShooterSingle extends AbstractPeaShooter {

    public PeaShooterSingle(MapLawn mapLawn) {
        super(mapLawn);
        this.setAction("shooting");
    }



    @Override
    protected PSShootBehavior pSShootBehavior() {
        return new PSShootBehavior(this) {
            @Override
            protected boolean needProduceBulletInShooting() {
                return this.getPlant().getAnimation().preciseFrame(12);
            }
        };
    }
}
