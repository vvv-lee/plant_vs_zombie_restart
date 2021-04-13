package game.behavior.plant.shoot;

import game.config.bullect.BulletCard;
import game.main.Game;
import game.main.GameScene;
import game.spirits.interfaces.Bullet;
import game.spirits.interfaces.Plant;

import java.util.List;

public abstract class DefaultShootBehavior extends ShootBehaviorHandler {

    private BulletCard bulletCard;

    public DefaultShootBehavior(Plant plant, BulletCard bulletCard) {
        super(plant);
        this.bulletCard = bulletCard;
    }

    @Override
    protected abstract boolean needProduceBulletInShooting();

    @Override
    protected abstract List<Bullet> productionBullet();

    @Override
    public boolean needBeginShoot() {
        Plant plant = getPlant();
        double x = plant.getX();
        Integer rowNum = plant.getRowNum();
        return Game.getGameMap().getMapData().haveZombie(x, GameScene.gameWidth, rowNum);
    }

    @Override
    protected BulletCard bulletCard() {
        return bulletCard;
    }
}
