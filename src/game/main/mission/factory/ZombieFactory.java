//package game.main.mission.factory;
//
//
//import game.spirits.interfaces.Zombie;
//
//
//import java.util.*;
//import java.util.concurrent.LinkedBlockingDeque;
//
//public class ZombieFactory {
//
//    private List<Zombie> liveZombieList = new ArrayList<>();
//
//    private ZombieFactoryBuilder.Assault useAssault;
//
//    private int residueZombieNum;
//
//    private int residueTime = 9999;
//
//    private LinkedBlockingDeque<ZombieFactoryBuilder.Assault> assaultQueue;
//
//    private Map<Integer, Double> rowWeightMap = this.rowWeightMap(5);
//
//
//    public ZombieFactory(LinkedBlockingDeque<ZombieFactoryBuilder.Assault> assaultQueue) {
//        this.assaultQueue = assaultQueue;
//        this.beginNextAssault();
//
//    }
//
//    public boolean allOver() {
//        return assaultOver() && assaultQueue.size() == 0;
//    }
//
//    public boolean assaultOver() {
//        return useAssault == null || (residueTime <= 0 && residueZombieNum == 0 && liveZombieList.size() == 0);
//    }
//
//    public List<Zombie> nextFrame() {
//        if (assaultOver()) {
//            return Collections.emptyList();
//        }
//        return thisAssault();
//    }
//
//
//    public void beginNextAssault() {
//
//
//        useAssault = assaultQueue.poll();
//        if (useAssault == null) throw new RuntimeException("tong guan");
//        residueTime = useAssault.getTime();
//        residueZombieNum = useAssault.getZombieNum();
//        rowWeightMap = this.rowWeightMap(5);
//
//    }
//
//    public List<Zombie> thisAssault() {
//
//        this.liveZombieList.removeIf(Zombie::needRemove);
//        if (residueZombieNum <= 0 && liveZombieList.size() == 0 && residueTime > 10) {
//            residueTime = 10;
//        }
//        if (residueTime <= 0 || residueZombieNum <= 0) {
//            residueTime--;
//            return Collections.emptyList();
//        }
//
//        int thisZombieNum = residueZombieNum / residueTime;
//        double s = (double) residueZombieNum / residueTime - thisZombieNum;
//
//
//        double defaultSpeed = (double) (useAssault.getZombieNum() - 1) / useAssault.getTime();
//        double nowSpeed = (double) (useAssault.getZombieNum() - residueZombieNum) / (useAssault.getTime() - residueTime);
//        if (nowSpeed > defaultSpeed) s = s / 10;//通过已经出现僵尸的速度 防止大量僵尸一起出现
//        else s = Math.min(0.99, s * 10);
//
//        double random = Math.random();
//
//        if (random < s) thisZombieNum++;
//
//
//        residueTime--;
//        residueZombieNum = residueZombieNum - thisZombieNum;
//
//
//        Map<String, Integer> weightMap = useAssault.getWeightMap();
//        List<Zombie> result = new ArrayList<>(thisZombieNum);
//        //防止僵尸在同一列出现
//        Integer sum = weightMap.values().stream().reduce(0, Integer::sum);
//
//        int index = 0;
//        double rowWeightSum = rowWeightMap.values().stream().reduce((double) 0, Double::sum);
//        while (index < thisZombieNum) {
//            random = Math.random() * sum;
//            String zombieName = "";
//            for (Map.Entry<String, Integer> entry : weightMap.entrySet()) {
//                random = random - entry.getValue();
//                if (random < 0) {
//                    zombieName = entry.getKey();
//                    break;
//                }
//            }
//            random = Math.random() * rowWeightSum;
//
//            int rowNum = 0;
//            for (Map.Entry<Integer, Double> entry : rowWeightMap.entrySet()) {
//                random = random - entry.getValue();
//                if (random <= 0) {
//                    rowNum = entry.getKey();
//                    break;
//                }
//            }
//            double subtraction = rowWeightMap.get(rowNum) / 4;
//            rowWeightSum = rowWeightSum - rowWeightMap.get(rowNum) * 3 / 4;
//
//            rowWeightMap.put(rowNum, subtraction);
//            result.add(ZombieFactory.newZombie(zombieName, rowNum));
//            index++;
//        }
//        this.liveZombieList.addAll(result);
//        return result;
//    }
//
//
//    private Map<Integer, Double> rowWeightMap(int num) {
//        Map<Integer, Double> rowWeightMap = new HashMap<>();
//        int i = 0;
//        while (i < num) {
//            rowWeightMap.put(i, (double) 1);
//            i++;
//
//        }
//        return rowWeightMap;
//
//    }
//
//    public static Zombie newZombie(String name, int rowNum) {
//        Zombie result;
//        if (name == null || name.equals("")) return new NormalZombie(0, rowNum);
//        name = name.toLowerCase();
//        if (name.contains("normal") || name.contains("普通")) result = new NormalZombie(0, rowNum);
//        else if (name.contains("flag") || name.contains("旗")) result = new FlagZombie(0, rowNum);
//        else if (name.contains("bucket") || name.contains("桶")) result = new BucketTheadZombie(0, rowNum);
//        else if (name.contains("cone")) result = new ConeHeadZombie(0, rowNum);
//        else if (name.contains("door")) result = new DoorZombie(0, rowNum);
//        else if (name.contains("extra")) result = new ExtraZombie(name.substring(12), 0, rowNum);
//
//        else result = new ExtraZombie(name, 0, rowNum);
//        return result;
//    }
//
//
//}
