package game.spirits.plants.shroom;

import game.config.bullect.BulletCard;
import game.main.Game;
import game.main.map.MapLawn;
import game.spirits.interfaces.Bullet;
import game.behavior.plant.base.ShroomBehavior;
import game.behavior.plant.shoot.DefaultShootBehavior;

import java.util.Collections;
import java.util.List;

public class ScaredyShroom extends AbstractShroom {
    public ScaredyShroom(MapLawn mapLawn) {
        super(mapLawn);
        addShootBehavior(
                new DefaultShootBehavior(this, BulletCard.getCard("pea")) {
                    @Override
                    protected boolean needProduceBulletInShooting() {
                        return this.getPlant().getAnimation().preciseFrame(12);
                    }
                    @Override
                    protected List<Bullet> productionBullet() {
                        return Collections.singletonList(bulletCard()
                                .newTranslationalBullet(getPlant()));
                    }
                }
        );
    }

    @Override
    protected ShroomBehavior shroomBaseBehavior() {
        return new ShroomBehavior() {
            @Override
            public boolean canUpdate(String oldAction) {
                return super.canUpdate(oldAction) && !isScared(oldAction);
            }

            @Override
            public String updateBehavior(String oldAction) {
                Integer rowNum = ScaredyShroom.this.getRowNum();
                double x = ScaredyShroom.this.getX();
                boolean haveZombie = Game.getGameMap().getMapData().haveZombie(
                        x - 150, x + 150, rowNum - 1, rowNum, rowNum + 1);
                if (haveZombie) return scared(oldAction);
                else if (isScared(oldAction)) return null;
                return super.updateBehavior(oldAction);
            }

            @Override
            protected boolean constraintWakeUp() {
                return constraintWakeUp;
            }

            private String scared(String oldAction) {
                if (oldAction.equals("scared")) {
                    if (getAnimation().preciseLastFrame()) return "scaredidle";
                } else if (!oldAction.equals("scaredidle")) {
                    return "scared";
                }
                return oldAction;
            }

            private boolean isScared(String action) {
                return action.equals("scaredidle") || action.equals("scared");
            }


        };
    }

//    @Override
//    protected void update() {
//        super.update();
////        Integer rowNum = this.getRowNum();
////        boolean haveZombie = Game.getGameMap().getMapData().haveZombie(
////                this.getX() - 150, this.getX() + 150, rowNum - 1, rowNum, rowNum + 1);
////        if (haveZombie) scared();
////        else stopScared();
//
//    }

//    private void stopScared() {
//        if (isScared(action)) this.setAction(plantCard().getDefaultAction());
//    }
//
//    private boolean isScared(String action) {
//        return action.equals("scaredidle") || action.equals("scared");
//    }
//
//    private void scared() {
//        if (this.action.equals("scared")) {
//            if (this.animation.preciseLastFrame()) this.setAction("scaredidle");
//        } else if (!this.action.equals("scaredidle")) {
//            this.setAction("scared");
//        }
//    }
}
