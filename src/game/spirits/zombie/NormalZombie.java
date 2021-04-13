package game.spirits.zombie;

import game.spirits.interfaces.Zombie;

public class NormalZombie extends AbstractZombie implements Zombie {

    public NormalZombie(double y, double x) {
        super(y, x);
    }



    @Override
    public double getYSpeed() {
        return super.getYSpeed();
    }


    @Override
    public boolean needRemove() {
        return false;
    }
}
