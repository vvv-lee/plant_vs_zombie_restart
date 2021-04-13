package game.spirits.bullet;

import game.config.bullect.BulletCard;
import game.spirits.interfaces.Coordinate;

public class ByThrowBullet extends TranslationalBullet {


    public ByThrowBullet(BulletCard bulletCard, double y, double x) {
        super(bulletCard, y, x);
    }

    public void throwTo(Coordinate coordinate) {

    }
}
