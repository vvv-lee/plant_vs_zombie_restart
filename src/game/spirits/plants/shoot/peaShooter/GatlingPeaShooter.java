package game.spirits.plants.shoot.peaShooter;

import game.main.map.MapLawn;
import game.behavior.plant.shoot.PSShootBehavior;

public class GatlingPeaShooter extends AbstractPeaShooter {
    private static int[] shootNums = new int[]{12, 18, 24, 30};

    public GatlingPeaShooter(MapLawn mapLawn) {
        super(mapLawn);
    }

    @Override
    protected PSShootBehavior pSShootBehavior() {
        return new PSShootBehavior(this) {
            @Override
            protected boolean needProduceBulletInShooting() {
                for (int shootNum : shootNums) {
                    if (getAnimation().preciseFrame(shootNum)) return true;
                }
                return false;
            }
        };
    }

}
