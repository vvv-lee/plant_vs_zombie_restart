//package game.spirits.zombie.base;
//
//
//import game.spirits.interfaces.Plant;
//
//
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.util.Collection;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.Map;
//
//public class ZombieStatus {
//
//
//    private AbstractZombie belongZombie;
//
//    private boolean statusUpdate = false;
//
//    private double xSpeedRepetition = 1;
//
//    private double ySpeedRepetition = 1;
//
//    private Map<Class<? extends Plant>, Integer> tummyMap = new HashMap<>(1);
//
//    private Map<ZombieBuff, Integer> buffDurationFrameMap = new HashMap<>();
//
//    public ZombieStatus(AbstractZombie belongZombie) {
//        this.belongZombie = belongZombie;
//    }
//
//    protected boolean haveBuff(ZombieBuff zombieBuff) {
//        Integer durationFrame = buffDurationFrameMap.get(zombieBuff);
//        return durationFrame != null && durationFrame > 0;
//    }
//
//
//    protected void update() {
//        if (statusUpdate) {
//            xSpeedRepetition = buildXSpeedRepetition();
//            ySpeedRepetition = buildYSpeedRepetition();
//            statusUpdate = false;
//        }
//        checkBuff();
//    }
//
//    protected boolean needRemoveByBuff() {
//        if (haveBuff(ZombieBuff.vanish)) return true;//消失 比如被吃了
//        if (haveBuff(ZombieBuff.deepIce) && (belongZombie.haveDied())) return true;//极度冰冻死了
//        return false;
//    }
//
//    protected int buildNowAttack(int attack) {
//        if (RougeBuff.压缩坚果.takeEffect()) {
//            Integer num = this.tummyMap.get(WallNut.class);
//            if (num != null && num >= 10) return 0;
//        }
//        return attack;
//    }
//
//    protected double buildRepetitionWithBuff(double repetition) {
//        for (ZombieBuff buff : buffDurationFrameMap.keySet()) {
//            repetition = buff.getShowF().apply(repetition);
//        }
//        return repetition;
//    }
//
//    protected boolean addBuff(Collection<ZombieBuff> buffEnums) {
//        if (buffEnums.size() == 0) return false;
//        int oldSize = buffEnums.size();
//        for (ZombieBuff buffEnum : buffEnums) {
//            if (buffEnum.equals(ZombieBuff.ice) || buffEnum.equals(ZombieBuff.deepIce)) {
//                if (!buffDurationFrameMap.containsKey(buffEnum)) {
//                    Sounds.ice.play();
//                }
//            }
//            buffDurationFrameMap.put(buffEnum, buffEnum.getDurationFrame());
//        }
//        boolean buffChange = buffDurationFrameMap.size() != oldSize;
//        if (buffChange) this.stateChange();
//        return buffChange;
//    }
//
//    protected void addPlantIntoTummy(Plant plant) {
//        Class<? extends Plant> plantClass = plant.getClass();
//        tummyMap.putIfAbsent(plantClass, 0);
//        tummyMap.put(plantClass, tummyMap.get(plantClass) + 1);
//    }
//
//    protected BufferedImage buildShowImage(BufferedImage oldImage) {
//        if (diedByHammerHurt()) return GameUtil.getImage("pic/pow.png");
//        if (this.buffDurationFrameMap.size() == 0) return oldImage;
//        BufferedImage result = oldImage;
//        for (ZombieBuff buff : this.buffDurationFrameMap.keySet()) {
//            result = buff.getImageFunction().apply(result);
//        }
//        return result;
//    }
//
//    private boolean diedByHammerHurt() {
//        return belongZombie.getHp() < 0 && haveBuff(ZombieBuff.hurtByHammer);
//    }
//
//    protected void drawOtherByBuff(Graphics graphics, BufferedImage zombieImage) {
//        if (!haveBuff(ZombieBuff.deepIce)) return;
//
//        int height = zombieImage.getHeight();
//        int width = zombieImage.getWidth();
//
//        graphics.drawImage(GameUtil.getImage("pic/Buff/deepIce.png"),
//                belongZombie.getX() + width / 2,
//                belongZombie.getY() + height - 20, null);
//
//    }
//
//
//    private void stateChange() {
//        statusUpdate = true;
//    }
//
//
//    public double buildXSpeed(double xSpeed) {
//        return xSpeed * xSpeedRepetition;
//    }
//
//    public double buildYSpeed(double ySpeed) {
//        return ySpeed * ySpeedRepetition;
//    }
//
//    private double buildXSpeedRepetition() {
//        double result = 1;
//        for (ZombieBuff zombieSpecialStatus : buffDurationFrameMap.keySet()) {
//            result = zombieSpecialStatus.getXf().apply(result);
//        }
//        return result;
//    }
//
//    private double buildYSpeedRepetition() {
//        double result = 1;
//        for (ZombieBuff zombieSpecialStatus : buffDurationFrameMap.keySet()) {
//            result = zombieSpecialStatus.getYf().apply(result);
//        }
//        return result;
//    }
//
//    protected void checkBuff() {
//        ZombieBuff hurtByHammer = ZombieBuff.hurtByHammer;
//        Map<ZombieBuff, Integer> oldMap = new HashMap<>(buffDurationFrameMap);
////被锤子打死就地消失
//        if (belongZombie.getHp() <= 0
//                && buffDurationFrameMap.containsKey(hurtByHammer)
//                && buffDurationFrameMap.get(hurtByHammer) == 0
//        ) {
//            buffDurationFrameMap.remove(hurtByHammer);
//            this.addBuff(Collections.singletonList(ZombieBuff.vanish));
//        }
//        if (tummyHaveMushroom() && RougeBuff.云南野生菌.takeEffect()) {
//            ZombieBuff poison = ZombieBuff.poison;
//            if (!haveBuff(poison)) {
//                Integer timer = buffDurationFrameMap.get(poison);
//                if (timer == 0) {
//                    double hurt = checkPoisonHurt() * belongZombie.maxHp;
//                    belongZombie.beHurt(hurt);
//                }
//                this.addBuff(Collections.singletonList(ZombieBuff.poison));
//            }
//        }
//        this.buffDurationFrameMap.keySet().removeIf(i -> buffDurationFrameMap.get(i) == 0);
//
//        for (ZombieBuff buffEnum : this.buffDurationFrameMap.keySet()) {
//            Integer durationFrame = this.buffDurationFrameMap.get(buffEnum);
//            this.buffDurationFrameMap.put(buffEnum, durationFrame - 1);
//        }
//        if (this.buffDurationFrameMap.containsKey(ZombieBuff.beatBack)) {
//            belongZombie.setX(belongZombie.getX() + 3.75);
//        }
//        if (!oldMap.equals(this.buffDurationFrameMap)) stateChange();
//    }
//
//    protected boolean tummyHaveMushroom() {
//        for (Class<? extends Plant> plantClass : tummyMap.keySet()) {
//            if (Mushroom.class.isAssignableFrom(plantClass)) return true;
//        }
//        return false;
//    }
//
//    //中毒伤害
//    protected double checkPoisonHurt() {
//        double hurt = 0;
//        for (Map.Entry<Class<? extends Plant>, Integer> entry : tummyMap.entrySet()) {
//            Class<? extends Plant> plantClass = entry.getKey();
//            if (Mushroom.class.isAssignableFrom(plantClass)) {
//                Integer value = entry.getValue();
//                if (!RougeBuff.环境影响.takeEffect()) value = 1;
//                hurt = hurt + 0.04 * value;
//                if (!RougeBuff.种类齐全.takeEffect()) break;
//            }
//        }
//        if (belongZombie.isMoving() && RougeBuff.肠胃蠕动.takeEffect()) hurt = hurt * 2;
//        return hurt;
//    }
//
//
//}
