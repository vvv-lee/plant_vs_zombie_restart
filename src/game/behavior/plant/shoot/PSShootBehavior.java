package game.behavior.plant.shoot;

import game.config.bullect.BulletCard;
import game.spirits.bullet.BulletBuilder;
import game.spirits.interfaces.Bullet;
import game.spirits.interfaces.Plant;

import java.util.Collections;
import java.util.List;

public abstract class PSShootBehavior extends DefaultShootBehavior {

    private static BulletCard bulletCard = BulletCard.getCard("pea");

    private static BulletBuilder bulletBuilder = BulletBuilder.builder().card("pea");

    public PSShootBehavior(Plant plant) {
        super(plant, bulletCard);
    }


    protected abstract boolean needProduceBulletInShooting();


    @Override
    protected List<Bullet> productionBullet() {

        List<Bullet> result = Collections.singletonList(
                bulletBuilder.coordinate(getPlant()).build());
        result.forEach(bullet -> bullet.offSet(-40, 72));
        return result;
    }


}
