package game.spirits.zombie;

import game.config.ProtectiveCard;

public class BucketZombie extends AbstractZombie {
    public BucketZombie(double y, double x) {
        super(y, x);
        addProtective(ProtectiveCard.getProtectiveCardByName("bucket"));
    }

}
