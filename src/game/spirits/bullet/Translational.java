package game.spirits.bullet;

import game.config.bullect.BulletCard;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Translational {

    private BulletCard bulletCard;

    private double nowXSpeed = 0;

    private double nowYSpeed = 0;

    private int timer = 0;

    private List<Integer> timerList = new LinkedList<>();

    private List<Double> angleList = new LinkedList<>();

    public Translational(BulletCard bulletCard) {
        this.bulletCard = bulletCard;
    }

    public void update() {
        if (timer == -1 && timerList.size() == 0) return;
        timer--;
        if (timer > 0) return;
        double angle = 0;
        if (timerList.size() == 0) {
            timer = -1;
        } else {
            timer = timerList.remove(0);
            angle = angleList.remove(0);
        }
        double radians = Math.toRadians(angle);
        nowXSpeed = bulletCard.getMaxSpeed() * Math.cos(radians);
        nowYSpeed = bulletCard.getMaxSpeed() * Math.sin(radians);
    }


    protected double xSpeed() {
        return nowXSpeed;
    }

    protected double ySpeed() {
        return nowYSpeed;
    }

    protected void newTranslational(int timer, double angle) {
        timerList.add(timer);
        angleList.add(angle);

    }


}


