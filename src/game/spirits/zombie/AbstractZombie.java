package game.spirits.zombie;

import game.config.ProtectiveCard;
import game.config.zombie.ZombieAction;
import game.main.Game;
import game.main.map.MapData;
import game.main.map.MapInfo;
import game.main.map.MapLawn;
import game.resourceUtil.animation.Animation;
import game.spirits.MoveSprite;
import game.spirits.interfaces.Bullet;
import game.spirits.interfaces.Plant;
import game.spirits.interfaces.Zombie;
import game.spirits.zombie.base.ZombieBody;

import java.util.List;


public class AbstractZombie extends MoveSprite implements Zombie {

    private ZombieBody zombieBody;


    public AbstractZombie(double y, double x) {
        this.y = y;
        this.x = x;
        this.newAction(ZombieAction.move);
    }

    @Override
    public void eatPlant(Plant plant) {
        this.newAction(ZombieAction.attack);
        if (getAnimation().preciseLastFrame()) {
            plant.beAttack(1);
        }
    }

    @Override
    public void newAction(ZombieAction zombieAction) {
        this.setAction(zombieAction.getName());
    }

    @Override
    public boolean crashBullet(Bullet bullet) {

        return zombieBody.crashBullet(bullet);
    }

    @Override
    public void hitByBullet(Bullet bullet) {
        zombieBody.beAttack(bullet.getAttack());
    }

    @Override
    public double beAttack(double attack) {
        return zombieBody.beAttack(attack);
    }

    @Override
    public void addProtective(ProtectiveCard protectiveCard) {
        if (!zombieCard().canUseProtective(protectiveCard)) {
            throw new RuntimeException("不是同一个动画无法添加");
        }
        zombieBody.addProtective(protectiveCard.createNew());
    }

    @Override
    protected double getThisActionFrameSpeed() {
        if (action.equals("move")) return 1 / 2.0;
        return 1 / 2.0;
    }


    @Override
    protected void update() {
        MapInfo mapInfo = Game.getGameMap().getMapInfo();
        int row = mapInfo.rowByY(getY());
        List<MapLawn> mapLawns = Game.getGameMap().getMapData().getMapLawnsList().get(row);
        boolean needEat = false;
        for (MapLawn mapLawn : mapLawns) {
            Plant plant = mapLawn.getPlant();
            if (plant != null && crash(plant)) {
                eatPlant(plant);
                needEat = true;
            }
        }
        if (!needEat) {
            this.newAction(ZombieAction.move);
        }
    }

    @Override
    public double getXSpeed() {
        return -getAnimation().getXSpeed();
    }

    @Override
    public double getYSpeed() {
        return getAnimation().getYSpeed();
    }

    @Override
    protected Animation buildAnimation() {
        this.zombieBody = new ZombieBody(this);
        return zombieBody;
    }

    @Override
    public double getHp() {
        return zombieBody.getHp();
    }

    @Override
    public boolean needRemove() {
        return false;
    }
}
