//package game.spirits.plants;
//
//import com.google.common.collect.ImmutableMap;
//import game.main.map.MapLawn;
//import game.spirits.interfaces.Recyclable;
//import game.spirits.util.Production;
//
//import java.util.List;
//import java.util.Map;
//
//public abstract class ProducePlant extends AbstractPlant {
//
//    protected int maxIntervalFrame = 300;
//
//    protected int productionFrame = 60;
//
//    protected int statusTimer = 0;
//
//    protected ProduceStatus status;
//
//    protected Map<ProduceStatus, Integer> maxFrameMap;
//
//    public ProducePlant(MapLawn mapLawn) {
//        super(mapLawn);
//        status = ProduceStatus.idle;
//        maxFrameMap = ImmutableMap.of(
//                ProduceStatus.idle, maxIntervalFrame,
//                ProduceStatus.ing, productionFrame,
//                ProduceStatus.over, productionFrame
//        );
//    }
//
//    @Override
//    protected Production doAction() {
//        Production result = super.doAction();
//        if (needProduction())
//            result.newRecyclables(productionRecyclable());
//        return result;
//
//    }
//
//    public boolean needProduction() {
//        return ProduceStatus.over.equals(status) && statusTimer == 0;
//    }
//
//    @Override
//    protected void update() {
//        statusUpdate();
//    }
//
//    public void newStatus(ProduceStatus status) {
//        this.status = status;
//        this.statusTimer = 0;
//    }
//
//    public void statusUpdate() {
//        statusTimer++;
//        if (maxFrameMap.get(this.status) == statusTimer) newStatus(this.status.next());
//
//    }
//
//    public abstract List<Recyclable> productionRecyclable();
//
//    public double getBrightness() {
//        return status.buildBrightness(statusTimer, 0.4, productionFrame);
//    }
//}
//
//enum ProduceStatus {
//    ing(),
//    over(),
//    idle();
//    protected static Map<ProduceStatus, ProduceStatus> statusMap = ImmutableMap.of(
//            idle, ing,
//            ing, over,
//            over, idle);
//
//    public ProduceStatus next() {
//        return statusMap.get(this);
//    }
//
//    public double buildBrightness(int statusTimer, double maxLight, int productionFrame) {
//        if (idle.equals(this)) return 0;
//        double brightness = (double) statusTimer / (double) productionFrame;
//        if (over.equals(this)) {
//            brightness = (productionFrame - statusTimer) / (double) productionFrame;
//        }
//        return brightness * maxLight;
//    }
//
//
//}
//
//
