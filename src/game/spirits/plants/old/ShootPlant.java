//package game.spirits.plants;
//
//import game.config.bullect.BulletCard;
//import game.main.map.GameMap;
//import game.main.map.MapLawn;
//import game.spirits.interfaces.Bullet;
//import game.spirits.util.Production;
//
//import java.util.List;
//
//public abstract class ShootPlant extends AbstractPlant {
//    protected BulletCard bulletCard;
//
//    public ShootPlant(MapLawn mapLawn) {
//        super(mapLawn);
//        this.bulletCard = bulletCard();
//    }
//
//    @Override
//    protected Production doAction() {
//        Production production = super.doAction();
//        if (needProductionBullet()) production.newBullets(productionBullet());
//        return production;
//
//    }
//
//
//    protected boolean isShooting() {
//        return this.action.equals("shooting");
//    }
//
//
//    protected boolean needProductionBullet() {
//        return isShooting() && getAnimation().preciseLastFrame();
//    }
//
//    @Override
//    public void updateBehaviorByMap(GameMap gameMap) {
//        super.updateBehaviorByMap(gameMap);
//        if (needShoot(gameMap)) beginShoot();
//        else stopShoot();
//    }
//
//    private void beginShoot() {
//        this.setAction("shooting");
//    }
//
//    public void stopShoot() {
//        this.setAction("idle");
//    }
//
//    protected abstract boolean needShoot(GameMap gameMap);
//
//    protected BulletCard getBulletCard() {
//        return bulletCard;
//    }
//
//    protected abstract List<Bullet> productionBullet();
//
//    protected abstract BulletCard bulletCard();
//
//}
