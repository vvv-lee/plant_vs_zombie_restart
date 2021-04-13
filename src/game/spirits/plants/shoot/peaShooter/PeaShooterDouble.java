package game.spirits.plants.shoot.peaShooter;

import game.main.map.MapLawn;
import game.behavior.plant.shoot.PSShootBehavior;

public class PeaShooterDouble extends AbstractPeaShooter {


    public PeaShooterDouble(MapLawn mapLawn) {
        super(mapLawn);

    }

    @Override
    protected PSShootBehavior pSShootBehavior() {
        return new PSShootBehavior(this) {
            private boolean overDouble = false;

            @Override
            protected boolean needProduceBulletInShooting() {
                if (getAnimation().preciseFrame(16)) {
                    if (!overDouble) {
                        getAnimation().toFrame(10);
                        overDouble = true;
                    } else {
                        overDouble = false;
                    }
                    return true;
                }
                return false;
            }
        };
    }

    private boolean overDouble = false;
    //
//    public PeaShooterDouble(MapLawn mapLawn) {
//        super(mapLawn);
//    }
//
//    @Override
//    protected void update() {
//        super.update();

//        if (getAnimation().preciseFrame(16)) {
//            if (!overDouble) {
//                getAnimation().toFrame(10);
//                overDouble = true;
//            } else {
//                overDouble = false;
//            }
//        }
//    }
}
