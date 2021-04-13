package game.spirits.bullet.decorator;

import game.spirits.interfaces.Bullet;
import game.spirits.interfaces.Zombie;

public class FireDecorator extends BulletDecorator {
    public FireDecorator(Bullet bullet) {
        super(bullet);
    }

    @Override
    public double getAttack() {
        return super.getAttack()*2;
    }

    @Override
    public boolean hurt(Zombie zombie) {
        return super.hurt(zombie);
    }

    @Override
    public void draw() {
        super.draw();
    }
}
