package game.spirits.plants.shroom;

import game.config.bullect.BulletCard;
import game.main.Game;
import game.main.map.MapLawn;
import game.spirits.interfaces.Bullet;
import game.spirits.interfaces.Plant;
import game.behavior.plant.shoot.ShootBehaviorHandler;

import java.util.Collections;
import java.util.List;

public class FumeShroom extends AbstractShroom {
    public FumeShroom(MapLawn mapLawn) {
        super(mapLawn);
        addShootBehavior(
                new ShootBehaviorHandler(this) {
                    @Override
                    protected boolean needProduceBulletInShooting() {
                        return this.getPlant().getAnimation().preciseFrame(12);
                    }
                    @Override
                    public boolean needBeginShoot() {
                        Plant plant = getPlant();
                        double x = plant.getX();
                        Integer rowNum = plant.getRowNum();

                        return Game.getGameMap().getMapData().haveZombie(x, 1400, rowNum);
                    }

                    @Override
                    protected List<Bullet> productionBullet() {
                        return Collections.singletonList(bulletCard().newTranslationalBullet(getPlant()));
                    }

                    @Override
                    protected BulletCard bulletCard() {
                        return BulletCard.getCard("pea");
                    }
                }
        );
    }

}
