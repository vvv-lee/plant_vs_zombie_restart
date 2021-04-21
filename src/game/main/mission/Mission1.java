package game.main.mission;


import game.main.mission.factory.ZombieFactory;
import game.main.mission.factory.ZombieFactoryBuilder;
import game.spirits.interfaces.Zombie;
import game.spirits.recyclable.Sun;


import java.util.List;

public class Mission1 implements Mission {

    private ZombieFactory zombieFactory;

    public Mission1() {
        super();
        this.zombieFactory = new ZombieFactoryBuilder()
                .newAssault().zombieNum(10).time(50)
                .addNewWeight("coneZombie", 10)
                .addNewWeight("normalZombie", 10)
                .addNewWeight("paperZombie", 10)
                .addNewWeight("bucketZombie", 100)
                .over().build();

    }

    @Override
    public List<List<Zombie>> newZombie() {
        return zombieFactory.nextFrame();
    }

    @Override
    public List<Sun> newSuns() {
        return null;
    }
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
}
