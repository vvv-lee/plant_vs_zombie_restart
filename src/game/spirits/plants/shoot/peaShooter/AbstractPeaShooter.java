package game.spirits.plants.shoot.peaShooter;

import game.main.map.MapLawn;

import game.behavior.plant.shoot.PSShootBehavior;
import game.behavior.plant.shoot.ShootBehaviorHandler;
import game.spirits.plants.leaf.SingleLeaf;
import game.spirits.plants.shoot.ShootPlant;


public abstract class AbstractPeaShooter extends ShootPlant {


    private SingleLeaf singleLeaf;

    public AbstractPeaShooter(MapLawn mapLawn) {
        super(mapLawn);
        this.singleLeaf = new SingleLeaf(this);
    }

    @Override
    public void draw() {
        double[] xyOffset = singleLeaf.getXYOffset();
        try {
            getAnimation().drawWithOffset(xyOffset[0], xyOffset[1]);
        }catch (Exception e){
            System.out.println(this.getClass().getSimpleName());
        }

        singleLeaf.draw();
    }

    @Override
    protected void nextFrame() {
        super.nextFrame();
        singleLeaf.nextFrame(getThisActionFrameSpeed());
    }

    protected abstract PSShootBehavior pSShootBehavior();

    @Override
    protected ShootBehaviorHandler shootBehavior() {
        return pSShootBehavior();
    }
}
