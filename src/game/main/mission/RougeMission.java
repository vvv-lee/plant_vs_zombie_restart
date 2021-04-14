//package game.main.mission;
//
//import game.enums.RougeBuff;
//import game.mission.factory.ZombieFactoryBuilder;
//import game.spirits.interfaces.Zombie;
//import game.spirits.zombies.*;
//
//import java.util.Collections;
//import java.util.List;
//
//public class RougeMission extends Mission {
//    int zombieNum = 1;
//    int time = 1;
//
//    public RougeMission() {
//        super();
//        newZombieFactory();
//    }
//
//    public int newSunNum() {
//        int i = 0;
//        int num = 0;
//        while (i < 3) {
//            if (Math.random() < 0.001) num++;
//            i++;
//        }
//        i = 0;
//        int result = num;
//        while (i < num) {
//            if (RougeBuff.刺眼阳光.takeEffect()) result++;
//            i++;
//        }
//        return result;
//    }
//
//
//    int assaultNum;
//
//    boolean assaultOver = false;
//
//
//    @Override
//    public boolean assaultOver() {
//        return zombieFactory == null || zombieFactory.allOver();
//    }
//
//    public void beginNextAssault() {
//        assaultNum++;
//        newZombieFactory();
//    }
//
//    public List<Zombie> nextFrameZombie() {
//        if (zombieFactory == null) {
//            newZombieFactory();
//        }
//        if (zombieFactory.allOver()) return Collections.emptyList();
//        return zombieFactory.nextFrame();
//
//    }
//
//    public void newZombieFactory() {
//        zombieFactory = new ZombieFactoryBuilder()
//                .newAssault().zombieNum(zombieNum).time(time)
////                .addNewWeight(NormalZombie.class, 10)
////                .addNewWeight(DoorZombie.class, 10)
////                .addNewWeight(BucketTheadZombie.class, 10)
////                .addNewWeight(FlagZombie.class, 5)
//                .over().build();
//        zombieNum = Double.valueOf(zombieNum * 1.5).intValue();
//        time = Double.valueOf(time * 1.2).intValue();
//    }
//
//}
