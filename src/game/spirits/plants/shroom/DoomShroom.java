package game.spirits.plants.shroom;

import game.main.map.MapLawn;

public class DoomShroom extends AbstractShroom {
    public DoomShroom(MapLawn mapLawn) {
        super(mapLawn);
    }

    public void update() {
        super.update();
        if (!isSleep()) {
            if (!this.action.equals("explode")) this.setAction("explode");
        }
    }


    @Override
    public boolean needRemove() {
        return super.needRemove() ||
                (this.action.equals("explode") && this.animation.preciseLastFrame());
    }
}
