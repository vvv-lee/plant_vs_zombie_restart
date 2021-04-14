package game.spirits.bullet;

import game.config.animation.Animations;
import game.config.bullect.BulletCard;
import game.config.bullect.BulletMoveType;
import game.spirits.interfaces.Bullet;
import game.spirits.interfaces.Coordinate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;

public class BulletBuilder {
    private static Logger logger = LoggerFactory.getLogger(BulletBuilder.class);
    private static Constructor<? extends TranslationalBullet> translationalConstructor;
    private static Constructor<? extends LockBullet> lockConstructor;

    static {
        try {
            translationalConstructor = TranslationalBullet
                    .class.getConstructor(BulletCard.class, double.class, double.class);
            lockConstructor = LockBullet
                    .class.getConstructor(BulletCard.class, double.class, double.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private BulletMoveType bulletMoveType = BulletMoveType.translational;
    private double y;
    private double x;
    private BulletCard bulletCard;

    public static BulletBuilder builder() {
        return new BulletBuilder();
    }

    public BulletBuilder card(String name) {
        this.bulletCard = BulletCard.getCard(name);
        return this;
    }

    public BulletBuilder lock() {
        bulletMoveType = BulletMoveType.lock;
        return this;
    }

    public BulletBuilder coordinate(Coordinate coordinate) {
        return coordinate(coordinate.getY(), coordinate.getX());
    }

    public BulletBuilder coordinate(double y, double x) {
        this.y = y;
        this.x = x;
        return this;
    }

    public Bullet build() {
        if (bulletCard == null) throw new RuntimeException("没有选择合适的子弹");
        try {

            if (bulletMoveType.equals(BulletMoveType.lock)) {
                return lockConstructor.newInstance(bulletCard, y, x);

            } else if (bulletMoveType.equals(BulletMoveType.bytThrow)) {
                return translationalConstructor.newInstance(bulletCard, y, x);
            } else {



                return      new TranslationalBullet(bulletCard,y,x);
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }

    }
}
