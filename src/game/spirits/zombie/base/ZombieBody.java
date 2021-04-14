package game.spirits.zombie.base;

import game.config.zombie.ZombieCard;
import game.animation.AbstractAnimation;
import game.animation.data.AnimationData;
import game.spirits.interfaces.Bullet;
import game.spirits.interfaces.Health;
import game.spirits.interfaces.Protective;
import game.spirits.interfaces.Zombie;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ZombieBody extends AbstractAnimation implements Health {


    private double hp;
    private double maxHp;

    private List<Protective> protectives = new ArrayList<>();


    private ZombieBodyData zombieBodyData;

    private List<AnimationData> showDataList = new ArrayList<>();

    private Map<String, String> actionMapping;


    private double crashHeight;
    private double beginX;
    private double crashWidth;
    private double beginY;


    public ZombieBody(Zombie zombie) {
        this.coordinate = zombie;
        this.init(zombie.zombieCard());
        update();
        this.animationData = showDataList.iterator().next();
        this.crashHeight = animationData.getCrashHeight();
        this.beginX = animationData.getBeginX();
        this.crashWidth = animationData.getCrashWidth();
        this.beginY = animationData.getBeginY();
    }


    private void init(ZombieCard zombieCard) {

        this.zombieBodyData = zombieCard.getZombieBodyData();

        this.maxHp = zombieCard.getMaxHp();
        this.hp = this.maxHp;
        this.actionMapping = zombieCard.newActionMapping();

    }


    @Override
    public double beAttack(double attack) {
        for (Protective protective : protectives) {
            attack = protective.beAttack(attack);
            if (attack == 0) break;
        }
        protectives.removeIf(i -> i.getHp() <= 0);
        if (hp <= attack) hp = 0;
        else hp = hp - attack;
        update();
        return 0;
    }


    public void update() {
        showDataList = zombieBodyData.statusUpdate(this.hp / this.maxHp, protectives);

    }


    public void addProtective(Protective protective) {
        this.protectives.add(protective);
        update();
    }


    @Override
    public void newAction(String action) {
        if(actionMapping.get(action)==null){
            throw new RuntimeException("没有行为"+action+"只有"+actionMapping.keySet());
        }
        super.newAction(actionMapping.get(action));
    }


    @Override
    public void draw() {
        this.drawWithOffset(0, 0);

    }

    @Override
    public void drawWithOffset(double offSetX, double offSetY) {
        for (AnimationData data : showDataList) {
            data.draw( nowAction, getRealPicFrameNum(),
                    coordinate.getX() + offSetX,
                    coordinate.getY() + offSetY);
        }
    }


    @Override
    public double getHp() {
        return hp;
    }

    public boolean crashBullet(Bullet bullet) {
        double x = bullet.getX();
        double y = bullet.getY();
        return x > this.beginX && x < this.beginX + crashWidth && y > this.beginY && y < beginY + crashHeight;
    }

//    default boolean xCrash(TetragonumCrash crash) {
//        if (crash.getCrashX() > getCrashX()) {
//            return crash.getCrashX() - crash.getCrashWidth() <= getCrashX() + getCrashWidth();
//        } else {
//            return getCrashX() - getCrashWidth() <= crash.getCrashX() + crash.getCrashWidth();
//        }
//    }
//
//    default boolean yCrash(TetragonumCrash crash) {
//        if (crash.getCrashY() > getCrashY()) {
//            return crash.getCrashY() - crash.getCrashHeight() <= getCrashY() + getCrashHeight();
//        } else {
//            return getCrashY() - getCrashHeight() <= crash.getCrashY() + crash.getCrashHeight();
//        }
//    }

}


