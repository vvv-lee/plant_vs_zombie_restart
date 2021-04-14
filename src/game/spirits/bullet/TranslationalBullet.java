package game.spirits.bullet;

import game.config.animation.Animations;
import game.config.bullect.BulletCard;
import game.animation.Animation;
import game.animation.SingleAnimation;
import game.spirits.MoveSprite;
import game.spirits.interfaces.Bullet;
import game.spirits.interfaces.Zombie;
import game.spirits.zombie.base.ZombieBuff;

import java.util.Collections;
import java.util.List;

public abstract class TranslationalBullet extends MoveSprite implements Bullet {

    protected BulletCard bulletCard;

    boolean haveHit = false;

    Translational translational;


    public TranslationalBullet newTranslational(int timer, double angle) {
        translational.newTranslational(timer, angle);
        return this;
    }

    @Override
    public double getXSpeed() {
        return translational.xSpeed();
    }

    @Override
    public double getYSpeed() {
        return translational.ySpeed();
    }



    public TranslationalBullet(BulletCard bulletCard, double y, double x) {
        this.y = y;
        this.x = x;
        this.bulletCard = bulletCard;
        this.animation = new SingleAnimation(this, bulletCard.getAnimationData());
        this.translational = new Translational(bulletCard);
        this.setAction("idle");
    }

    @Override
    public Bullet withBuffs(ZombieBuff zombieBuff) {
        return this;
    }

    @Override
    public List<ZombieBuff> buffToZombie() {
        return Collections.emptyList();
    }

    @Override
    protected void setThisAnimation() {

    }

    @Override
    public double getAttack() {
        return bulletCard.getBaseAttack();
    }

    @Override
    protected void update() {
        translational.update();
    }

    @Override
    protected Animation buildAnimation() {
        return new SingleAnimation(this,
                Animations.cacheMap.get(
                        this.getClass().getSimpleName().toLowerCase() + "-main"));
    }

    @Override
    public boolean needRemove() {
        return haveHit;
    }

    @Override
    public Bullet offSet(int offSetY, int offSetX) {
        this.y = this.y + offSetY;
        this.x = this.x + offSetX;
        return this;
    }

    @Override
    public boolean hurt(Zombie zombie) {
//        zombie.hitByBullet(this);
        haveHit = true;
        return true;
    }
}
