package game.spirits.interfaces;

public interface Move extends Sprite {

    double getXSpeed();

    double getYSpeed();

    void setXSpeed(double xSpeed);

    void setYSpeed(double ySpeed);

    default void move() {
        this.to(this.getY() + this.getYSpeed(), this.getX() + this.getXSpeed());
    }

}
