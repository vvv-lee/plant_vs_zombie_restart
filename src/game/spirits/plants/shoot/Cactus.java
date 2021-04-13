package game.spirits.plants.shoot;

import game.config.bullect.BulletCard;
import game.main.map.MapLawn;
import game.spirits.interfaces.Bullet;
import game.behavior.plant.shoot.ShootBehaviorHandler;
import game.behavior.plant.shoot.DefaultShootBehavior;

import java.util.Collections;
import java.util.List;

public class Cactus extends ShootPlant {
    public Cactus(MapLawn mapLawn) {
        super(mapLawn);
    }

    @Override
    protected ShootBehaviorHandler shootBehavior() {
        return new DefaultShootBehavior(this, BulletCard.getCard("CactusBullet")) {
            @Override
            protected boolean needProduceBulletInShooting() {
                return this.getPlant().getAnimation().preciseFrame(12);
            }
            @Override
            protected List<Bullet> productionBullet() {
                List<Bullet> result = Collections.singletonList(bulletCard().newTranslationalBullet(getPlant()));
                result.forEach(bullet -> bullet.offSet(-40, 72));
                return result;
            }
        };
    }
}
