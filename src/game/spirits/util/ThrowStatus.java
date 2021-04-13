package game.spirits.util;

public class ThrowStatus {
    private int throwToTopTime;
    private int throwTimer = 0;
    private double gravity;
    private double xSpeed;
    private double beginYSpeed;

    public static double randomXMoveValue(double value) {
        double xSpeed = value;
        double random = Math.random();
        if (random < (1.0 / 3)) xSpeed = -1 * value;
        else if (random < (2.0 / 3)) xSpeed = 0;
        return xSpeed;
    }


    public ThrowStatus(double throwHeight, int throwToTopTime, double xSpeed) {
        this.throwToTopTime = throwToTopTime;
        gravity = (2 * throwHeight / (throwToTopTime * throwToTopTime));
        beginYSpeed = -gravity * throwToTopTime;
        this.xSpeed = xSpeed;

    }

    public void update() {
        throwTimer++;
    }

    public boolean throwOver() {
        return throwTimer > throwToTopTime * 2;
    }

    public double getXSpeed() {
        if (throwOver()) return 0;
        else return xSpeed;
    }

    public double getYSpeed() {
        if (throwOver()) return 0;
        else return beginYSpeed + (throwTimer * gravity);

    }
}
