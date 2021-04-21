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
    private String realAction;

    private List<Protective> protectives = new ArrayList<>();


    private ZombieBodyData zombieBodyData;

    private List<AnimationData> showDataList = new ArrayList<>();

    private Map<String, String> actionMapping;


    public ZombieBody(Zombie zombie) {
        this.coordinate = zombie;
        this.init(zombie.zombieCard());
        update();
        this.animationData = showDataList.iterator().next();
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
        if (actionMapping.get(action) == null) {
            throw new RuntimeException("没有行为" + action + "只有" + actionMapping.keySet());
        }
        this.realAction = action;
        super.newAction(actionMapping.get(action));
    }

    @Override
    public String nowAction() {
        return realAction;
    }

    @Override
    public void draw() {
        this.drawWithOffset(0, 0);

    }

    @Override
    public void drawWithOffset(double offSetX, double offSetY) {
        for (AnimationData data : showDataList) {
            data.draw(nowAction, getRealPicFrameNum(),
                    coordinate.getX() + offSetX,
                    coordinate.getY() + offSetY);
        }
    }


    @Override
    public double getHp() {
        return hp;
    }

//    class FallDown {
//        AnimationData animationData;
//        private String nowAction;
//        private int realPicFrameNum;
//
//
//        double ySpeed;
//        double xSpeed;
//        double fallHeight;
//
//        public FallDown(AnimationData animationData, double ySpeed, double xSpeed, double fallHeight) {
//            this.animationData = animationData;
//            this.ySpeed = ySpeed;
//            this.xSpeed = xSpeed;
//            this.fallHeight = fallHeight;
//        }
//
//        public void update() {
//
//
//        }
//
//        public void drawWithOffset(double offSetX, double offSetY) {
//            var coordinate = ZombieBody.this.coordinate;
//
//            animationData.d
//            draw();
//        }
//    }


}


