package game.spirits.bullet;

import game.config.bullect.BulletCard;
import game.spirits.interfaces.Bullet;

public class LockBullet extends TranslationalBullet implements Bullet {

    BulletLock bulletLock;

    public LockBullet(BulletCard bulletCard, double y, double x) {
        super(bulletCard, y, x);
        bulletLock = new BulletLock(this);
    }

    @Override
    protected void update() {
        super.update();
        double angle = bulletLock.updateAndReturnNewAngle();
        newTranslational(1, angle);
    }

    @Override
    public void draw() {
        bulletLock.draw(animation);
    }

}
