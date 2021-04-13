package game.spirits.plants.shoot;

import game.config.bullect.BulletCard;
import game.main.Game;
import game.main.map.MapLawn;
import game.spirits.interfaces.Bullet;
import game.behavior.plant.shoot.ShootBehaviorHandler;
import game.behavior.plant.shoot.DefaultShootBehavior;

import java.util.Collections;
import java.util.List;

public class Cattail extends ShootPlant {

    public Cattail(MapLawn mapLawn) {
        super(mapLawn);
    }

    @Override
    protected double getThisActionFrameSpeed() {
        return 1 / 2.0;
    }

    @Override
    protected ShootBehaviorHandler shootBehavior() {
        return new DefaultShootBehavior(this, BulletCard.getCard("CactusBullet")) {
            private int intervalTimer = 0;
            private int shootNum = 0;

            @Override

            public boolean needBeginShoot() {
                if (intervalTimer > 0) {
                    intervalTimer--;
                    return false;
                }
                return Game.getGameMap().getMapData().haveZombie(0, 1400);
            }

            @Override
            protected boolean needProduceBulletInShooting() {
                boolean need = this.getPlant().getAnimation().preciseLastFrame();
                if (need) {
                    if (shootNum % 2 == 0) {
                        this.getPlant().getAnimation().toFrame(0);
                    } else {
                        Cattail.this.setAction("idle");
                        intervalTimer = 48;
                    }
                    shootNum++;
                }
                return need;

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
