package game.main.mission.factory;


import game.config.zombie.ZombieCard;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingDeque;

public class ZombieFactoryBuilder {

    private LinkedBlockingDeque<Assault> assaultQueue = new LinkedBlockingDeque<>();

    public ZombieFactory build() {
        return new ZombieFactory(assaultQueue);
    }

    public Assault newAssault() {
        Assault assault = new Assault(this);
        assaultQueue.add(assault);
        return assault;

    }

    private ZombieFactoryBuilder newAssaultOver(Assault assault) {
        if (assault.isUsePrevious()) {
            if (assaultQueue.size() > 0) {
                Assault last = assaultQueue.getLast();
                assault.getWeightMap().putAll(last.getWeightMap());
            } else {
                assault.defaultWeightMap();
            }
        }
        return this;

    }


    public static class Assault {
        private ZombieFactoryBuilder zombieFactoryBuilder;

        private Integer zombieNum;

        private Integer time;

        private Map<ZombieCard, Integer> weightMap = new HashMap<>();

        private boolean usePrevious = false;

        public Assault(ZombieFactoryBuilder zombieFactoryBuilder) {
            this.zombieFactoryBuilder = zombieFactoryBuilder;
        }


        public Assault previousWeightMap() {
            usePrevious = true;
            if (weightMap.size() != 0) this.weightMap = new HashMap<>();
            return this;

        }


        protected boolean isUsePrevious() {
            return usePrevious;
        }


        public Assault zombieNum(int zombieMum) {
            this.zombieNum = zombieMum;
            return this;
        }

        public Assault defaultWeightMap() {
            weightMap = new HashMap<>();
            weightMap.put(ZombieCard.zombieCard("normal"), 1);
            return this;
        }

        public Assault time(int time) {
            this.time = time;
            return this;
        }

        public Assault addNewWeight(String name, int weight) {
            if (!name.toLowerCase().contains("zombie")) {
                name = name + "zombie";
            }
            weightMap.put(ZombieCard.zombieCard(name), weight);
            return this;
        }

        public Assault remove(String name) {
            if (!name.toLowerCase().contains("zombie")) {
                name = name + "zombie";
            }
            weightMap.remove(ZombieCard.zombieCard(name));
            return this;
        }

        public ZombieFactoryBuilder over() {
            if (time == null || zombieNum == null) throw new RuntimeException("ZombieFactoryBuilder");
            if (weightMap == null || weightMap.size() == 0) usePrevious = true;
            return zombieFactoryBuilder.newAssaultOver(this);
        }


        protected int getZombieNum() {
            return zombieNum;
        }

        protected int getTime() {
            return time;
        }

        protected Map<ZombieCard, Integer> getWeightMap() {
            return weightMap;
        }

    }


}
