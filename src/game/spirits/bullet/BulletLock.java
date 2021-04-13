package game.spirits.bullet;

import game.config.bullect.BulletCard;
import game.main.Game;
import game.main.map.GameMap;
import game.resourceUtil.animation.Animation;
import game.spirits.interfaces.Bullet;
import game.spirits.interfaces.Coordinate;
import game.spirits.interfaces.Zombie;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Affine;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class BulletLock {
    private TranslationalBullet bullet;


    private Zombie lockZombie;

    private boolean haveTryLock = false;

    private double angle = 0;

    public BulletLock(TranslationalBullet bullet) {
        this(bullet, 0);
    }

    public BulletLock(TranslationalBullet bullet, double angle) {
        this.bullet = bullet;
        this.angle = angle;
    }

    public double updateAndReturnNewAngle() {
        if (lockZombie != null) {
            if (lockZombie.needRemove()) {
                lockZombie = null;
                return angle;
            }
            double x = lockZombie.getAnimation().centerX() - bullet.getAnimation().centerX();
            double y = lockZombie.getAnimation().centerY() - bullet.getAnimation().centerY();

            if (Math.abs(y) < 3) {
                if (y > 0) y = 2;
                else y = -2;
            }
            int wantAngle = Double.valueOf(Math.atan2(y, x) / Math.PI * 180).intValue();
            wantAngle = (wantAngle + 360) % 360;
            double clockwise = wantAngle - angle;
            if (angle > wantAngle) clockwise = 360 - angle + wantAngle;
            double anticlockwise = angle - wantAngle;
            if (angle < wantAngle) anticlockwise = 360 - wantAngle + angle;
            if (clockwise > anticlockwise) angle = ((angle - 2.5) + 360) % 360;
            else angle = ((angle + 2.5) + 360) % 360;
         } else {
            lockZombieFromMap();
        }
        return angle;

    }


    public boolean lockZombieFromMap() {
        GameMap gameMap = Game.getGameMap();
        if (haveTryLock) return true;
        List<List<Zombie>> zombieQueueList = gameMap.getZombieQueueList();
        double minDistance = 10000;
        for (List<Zombie> zombies : zombieQueueList) {
            if (zombies.size() == 0) continue;
            Zombie rowZombie = zombies.get(0);
            if (rowZombie != null) {
                if (lockZombie == null) lockZombie = rowZombie;
                double xDistance = rowZombie.getAnimation().centerX() - bullet.getAnimation().centerX();
                double yDistance = rowZombie.getAnimation().centerY() - bullet.getAnimation().centerY();
                double distance = Math.sqrt(xDistance * xDistance + yDistance * yDistance);
                if (distance < minDistance) {
                    lockZombie = rowZombie;
                    minDistance = distance;
                }
            }
        }
        haveTryLock = true;
        return lockZombie != null;
    }

    public void draw(Animation animation) {
//        double radians = Math.toRadians(angle);
//
        animation.withAngle(angle);
        animation.draw();
//////
////        double crashHeight = animation.getAnimationData().getCrashHeight();
////        double crashWidth = animation.getAnimationData().getCrashWidth();
////        double centerX = crashHeight / 2;
////        double centerY = crashWidth / 2;
////        double nowCenterX = (crashHeight * Math.sin(radians) + crashWidth * Math.cos(radians)) / 2;
////        double nowCenterY = (crashHeight * Math.cos(radians) + crashWidth * Math.sin(radians)) / 2;
////
////        double offsetX = (nowCenterX - centerX) / 2;
////        double offsetY = (nowCenterY - centerY) / 2;
//        GraphicsContext graphicsContext = Game.getGraphicsContext();
//
//        Affine affine = graphicsContext.getTransform();
//        affine.appendRotation(angle, bullet.getX(),
//                bullet.getY()
//                        - animation.getAnimationData().getCrashHeight());
//        graphicsContext.setTransform(affine);
//////
//        animation.draw();

    }

}
