package game.spirits.bullet.decorator;

import game.animation.Animation;
import game.spirits.interfaces.Bullet;
import game.spirits.interfaces.Zombie;
import game.spirits.util.Production;
import game.spirits.zombie.base.ZombieBuff;

import java.util.List;

public class BulletDecorator implements Bullet {

    private Bullet bullet;

    @Override
    public void to(double y, double x) {
        bullet.to(y,x);
    }

    @Override
    public Bullet withBuffs(ZombieBuff zombieBuff) {
        return bullet.withBuffs(zombieBuff);
    }

    @Override
    public List<ZombieBuff> buffToZombie() {
        return bullet.buffToZombie();
    }

    public BulletDecorator(Bullet bullet) {
        this.bullet = bullet;
    }

    @Override
    public Bullet offSet(int offSetY, int offSetX) {
        return bullet.offSet(offSetY, offSetX);
    }

    @Override
    public double getAttack() {
        return bullet.getAttack();
    }

    @Override
    public boolean hurt(Zombie zombie) {
        return bullet.hurt(zombie);
    }

    @Override
    public boolean needRemove() {
        return bullet.needRemove();
    }

    @Override
    public Production doActionAndUpdate() {
        return bullet.doActionAndUpdate();
    }

    @Override
    public Animation getAnimation() {
        return bullet.getAnimation();
    }

    @Override
    public double getX() {
        return bullet.getX();
    }

    @Override
    public double getY() {
        return bullet.getY();
    }

    @Override
    public void draw() {
        bullet.draw();

    }
}
