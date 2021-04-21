package game.behavior.plant.shoot;

import game.config.bullect.BulletCard;
import game.spirits.interfaces.Bullet;
import game.spirits.interfaces.Plant;
import game.behavior.BehaviorHandler;
import game.spirits.util.Production;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class ShootBehaviorHandler implements BehaviorHandler {

    private Plant plant;

    protected String shooting = "shooting";

    public ShootBehaviorHandler(Plant plant) {
        this.plant = plant;
    }

    @Override
    public boolean canUpdate(String action) {
        return true;
    }

    @Override
    public String updateBehavior(String action) {
        boolean needBeginShoot = this.needBeginShoot();
        String result;
        if (needBeginShoot) {
            result = shooting;
        } else {
            if (!actionIsShooting(action)) result = action;
            else result = null;
        }
        return result;
    }

    @Override
    public void addNewSprites(Production production) {
        production.newBullets(shoot());
    }

    private List<Bullet> shoot() {
        if (isShooting() && needProduceBulletInShooting()) {
            return productionBullet();
        }
        return new ArrayList<>(0);

    }

    private boolean isShooting() {
        return actionIsShooting(plant.nowAction());
    }


    protected static boolean actionIsShooting(String nowAction) {
        return Objects.equals(nowAction, "shooting");
    }

    protected abstract boolean needProduceBulletInShooting();

    protected abstract boolean needBeginShoot();

    protected abstract List<Bullet> productionBullet();

    protected abstract BulletCard bulletCard();

    public Plant getPlant() {
        return plant;
    }
}


