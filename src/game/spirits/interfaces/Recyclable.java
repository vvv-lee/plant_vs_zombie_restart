package game.spirits.interfaces;


import game.spirits.util.ThrowStatus;

public interface Recyclable extends Sprite, Click {


    default Recyclable throwIt() {
        return throwIt(50, 10, ThrowStatus.randomXMoveValue(0.5));
    }

    Recyclable throwIt(double throwHeight, int throwToTopTime, double xSpeed);

    boolean isRecycling();

    default boolean clickItem(int y, int x) {
        if (mouseOn(y, x) && !isRecycling()) {
            this.beginRecycle();
            return true;
        }
        return false;
    }

    void beginRecycle();

    int getValue();

    boolean successRecycle();
}
