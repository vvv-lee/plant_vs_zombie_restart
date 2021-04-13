package game.spirits.zombie;

import game.config.ProtectiveCard;

public class ConeZombie extends AbstractZombie {
    public ConeZombie(double y, double x) {
        super(y, x);
        addProtective(ProtectiveCard.getProtectiveCardByName("cone"));
    }

}
