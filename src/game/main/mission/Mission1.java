//package game.main.mission;
//
//import game.mission.factory.ZombieFactory;
//import game.mission.factory.ZombieFactoryBuilder;
//import game.spirits.interfaces.Zombie;
//import game.spirits.zombies.BucketTheadZombie;
//import game.spirits.zombies.FlagZombie;
//import game.spirits.zombies.NormalZombie;
//
//import java.util.List;
//
//public class Mission1 extends Mission {
//    public Mission1() {
//        super();
//
//        this.zombieFactory = new ZombieFactoryBuilder()
//                .newAssault().zombieNum(0).time(450)
//                .addNewWeight(NormalZombie.class, 10)
//                .addNewWeight(FlagZombie.class, 5).over()
//                .newAssault().previousWeightMap()
//                .time(1200).zombieNum(20)
//                .addNewWeight(BucketTheadZombie.class, 2).over()
//                .newAssault().previousWeightMap().addNewWeight(BucketTheadZombie.class, 10)
//                .time(1500).zombieNum(100).over().build();
//    }
//
//    @Override
//    public boolean assaultOver() {
//        return zombieFactory.assaultOver();
//    }
//
//    @Override
//    public void beginNextAssault() {
//        zombieFactory.beginNextAssault();
//    }
//
//    @Override
//    public List<Zombie> nextFrameZombie() {
//        if (assaultOver()) beginNextAssault();
//        return zombieFactory.nextFrame();
//    }
//
//    @Override
//    public ZombieFactory getZombieFactory() {
//        return zombieFactory;
//    }
//}
