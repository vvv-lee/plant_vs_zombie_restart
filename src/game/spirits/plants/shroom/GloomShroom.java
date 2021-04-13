package game.spirits.plants.shroom;

import game.behavior.plant.shoot.DefaultShootBehavior;
import game.config.bullect.BulletCard;
import game.main.Game;
import game.main.map.MapLawn;
import game.spirits.interfaces.Bullet;
import game.spirits.interfaces.Plant;
import game.behavior.plant.shoot.ShootBehaviorHandler;

import java.util.Collections;
import java.util.List;

public class GloomShroom extends AbstractShroom {
    public GloomShroom(MapLawn mapLawn) {
        super(mapLawn);
        addShootBehavior(
                new DefaultShootBehavior(this, BulletCard.getCard("pea")) {
                    @Override
                    protected boolean needProduceBulletInShooting() {
                        return this.getPlant().getAnimation().preciseFrame(12);
                    }

                    @Override
                    public boolean needBeginShoot() {
                        Plant plant = getPlant();
                        double x = plant.getX();
                        Integer rowNum = plant.getRowNum();
                        return Game.getGameMap().getMapData().haveZombie(x - 150, x + 150,
                                rowNum - 1,
                                rowNum, rowNum + 1);
                    }

                    @Override
                    protected List<Bullet> productionBullet() {
                        return Collections.singletonList(bulletCard().newTranslationalBullet(getPlant()));
                    }


                }
        );
    }
}
