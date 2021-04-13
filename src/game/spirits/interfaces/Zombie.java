package game.spirits.interfaces;

import game.config.ProtectiveCard;
import game.config.zombie.ZombieAction;
import game.config.zombie.ZombieCard;

public interface Zombie extends Sprite, Health {


    default ZombieCard zombieCard() {
        return ZombieCard.zombieCard(this.getClass().getSimpleName());
    }

    void newAction(ZombieAction zombieAction);

    void addProtective(ProtectiveCard protectiveCard);

    boolean crashBullet(Bullet bullet);

    void hitByBullet(Bullet bullet);

    void eatPlant(Plant plant);


}
