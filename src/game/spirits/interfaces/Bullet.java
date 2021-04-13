package game.spirits.interfaces;

import game.spirits.zombie.base.ZombieBuff;

import java.util.List;

public interface Bullet extends Sprite {

    Bullet offSet(int offSetY, int offSetX);

    Bullet withBuffs(ZombieBuff zombieBuff);

    double getAttack();

    boolean hurt(Zombie zombie);

    List<ZombieBuff> buffToZombie();


}
