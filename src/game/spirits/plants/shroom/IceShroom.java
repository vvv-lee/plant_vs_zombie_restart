package game.spirits.plants.shroom;

import game.main.map.MapLawn;

public class IceShroom extends AbstractShroom {
    public IceShroom(MapLawn mapLawn) {
        super(mapLawn);
    }

    @Override
    protected double getThisActionFrameSpeed() {
        if(this.action.equals("idle"))return 1/4.0;
        return super.getThisActionFrameSpeed();
    }

    @Override
    public boolean needRemove() {
        return super.needRemove() ||
                (this.action.equals("idle") && this.animation.animationOver());
    }
}
