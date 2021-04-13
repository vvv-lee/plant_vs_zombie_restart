package game.spirits.interfaces;

public interface Move {

    double getXSpeed();

    double getYSpeed();

    void setXSpeed(double xSpeed);

    void setYSpeed(double ySpeed);

    void move();

}
