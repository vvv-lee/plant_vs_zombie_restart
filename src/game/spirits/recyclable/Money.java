package game.spirits.recyclable;

public abstract class Money extends AbstractRecycle {


    public Money() {
    }

    @Override
    protected double getRecycleY() {
        return 625;
    }

    @Override
    protected double getRecycleX() {
        return 240;
    }

}
