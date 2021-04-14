package game.spirits.plants.shoot.peaShooter;

import game.main.map.MapLawn;
import game.behavior.plant.shoot.PSShootBehavior;

public class ThreePeaShooter extends AbstractPeaShooter {
    public ThreePeaShooter(MapLawn mapLawn) {
        super(mapLawn);
    }

    @Override
    protected PSShootBehavior pSShootBehavior() {
        return new PSShootBehavior(this) {
            @Override
            public String updateBehavior(String action) {
                String result = super.updateBehavior(action);
                if (result.equals(shooting)) return "shooting1";
                return result;
            }

            @Override
            protected boolean needProduceBulletInShooting() {
                return false;
            }
        };
    }
}
