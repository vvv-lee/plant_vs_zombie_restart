package game.spirits.zombie;

import game.config.ProtectiveCard;

public class FootBallZombie extends AbstractZombie {

    public FootBallZombie(double y, double x) {
        super(y, x);
        addProtective(ProtectiveCard.getProtectiveCardByName("helmet"));
    }

    @Override
    protected double getThisActionFrameSpeed() {
        return 1/2.0;
    }

}
