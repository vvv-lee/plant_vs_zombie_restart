//package game.main.mission.factory;
//
//
//import game.spirits.interfaces.Zombie;
//import game.spirits.zombies.AbstractZombie;
//
//import java.lang.reflect.Modifier;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.concurrent.LinkedBlockingDeque;
//
//public class ZombieFactoryBuilder {
//
//    private LinkedBlockingDeque<Assault> assaultQueue = new LinkedBlockingDeque<>();
//
//    public ZombieFactory build() {
//        return new ZombieFactory(assaultQueue);
//    }
//
//    public Assault newAssault() {
//        Assault assault = new Assault(this);
//        assaultQueue.add(assault);
//        return assault;
//
//    }
//
//    private ZombieFactoryBuilder newAssaultOver(Assault assault) {
//        if (assault.isUsePrevious()) {
//            if (assaultQueue.size() > 0) {
//                Assault last = assaultQueue.getLast();
//                assault.getWeightMap().putAll(last.getWeightMap());
//            } else {
//                assault.defaultWeightMap();
//            }
//        }
//        return this;
//
//    }
//
//
//    public static class Assault {
//        private ZombieFactoryBuilder zombieFactoryBuilder;
//
//        private Integer zombieNum;
//
//        private Integer time;
//
//        private Map<String, Integer> weightMap = new HashMap<>();
//
//        private boolean usePrevious = false;
//
//
//        public Assault(ZombieFactoryBuilder zombieFactoryBuilder) {
//            this.zombieFactoryBuilder = zombieFactoryBuilder;
//        }
//
//
//        public Assault previousWeightMap() {
//            usePrevious = true;
//            if (weightMap.size() != 0) this.weightMap = new HashMap<>();
//            return this;
//
//        }
//
//
//        protected boolean isUsePrevious() {
//            return usePrevious;
//        }
//
//
//        public Assault zombieNum(int zombieMum) {
//            this.zombieNum = zombieMum;
//            return this;
//        }
//
//        public Assault defaultWeightMap() {
//            weightMap = new HashMap<>();
//            weightMap.put("normal", 1);
//            return this;
//        }
//
//        public Assault time(int time) {
//            this.time = time;
//            return this;
//        }
//
//        public Assault addNewWeight(Class<? extends Zombie> z, int weight) {
//            boolean isAbstract = Modifier.isAbstract(z.getModifiers());
//            if(isAbstract)throw new RuntimeException(z.toString());
//
//
//            weightMap.put(z.getSimpleName(), weight);
//            return this;
//        }
//
//        public Assault remove(Class<? extends Zombie> z) {
//
//            weightMap.remove(z.getSimpleName());
//            return this;
//        }
//
//        public ZombieFactoryBuilder over() {
//            if (time == null || zombieNum == null) throw new RuntimeException("ZombieFactoryBuilder");
//            if (weightMap == null || weightMap.size() == 0) usePrevious = true;
//            return zombieFactoryBuilder.newAssaultOver(this);
//        }
//
//        protected Assault extraZombie(String name, int weight) {
//            weightMap.put("ExtraZombie " + name, weight);
//            return this;
//        }
//
//        protected int getZombieNum() {
//            return zombieNum;
//        }
//
//        protected int getTime() {
//            return time;
//        }
//
//        protected Map<String, Integer> getWeightMap() {
//            return weightMap;
//        }
//
//    }
//
//    public static void main(String[] args) {
//        Map<Class<? extends Zombie>, Integer> s = new HashMap<>();
//        s.put(AbstractZombie.class, 1);
//
//    }
//
//
//}
