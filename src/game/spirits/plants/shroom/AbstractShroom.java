package game.spirits.plants.shroom;

import game.main.map.MapLawn;
import game.behavior.plant.base.ShroomBehavior;
import game.spirits.plants.AbstractPlant;

public abstract class AbstractShroom extends AbstractPlant {
    private ZZZ zzz;

    protected double zzzXOffset = 50;

    protected double zzzYOffset = 10;

    protected boolean constraintWakeUp = false;//咖啡豆强制苏醒

    public AbstractShroom(MapLawn mapLawn) {
        super(mapLawn);
        addBaseBehavior(shroomBaseBehavior());
        this.zzz = new ZZZ(this);
        updateBehavior();
    }

    @Override
    public void draw() {
        super.draw();
        if (isSleep()) this.zzz.drawWithOffset(zzzXOffset, zzzYOffset);
    }

    @Override
    protected void nextFrame() {
        super.nextFrame();
        this.zzz.nextFrame(0.2);
    }

    protected boolean isSleep() {
        return this.action.equals("sleep");
    }

    protected ShroomBehavior shroomBaseBehavior() {
        return new ShroomBehavior() {
            @Override
            protected boolean constraintWakeUp() {
                return constraintWakeUp;
            }
        };
    }


}
