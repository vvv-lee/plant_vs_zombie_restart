package game.behavior.plant.base;

import game.main.Game;

public abstract class ShroomBehavior extends AbstractPlantBehaviorHandler {

    @Override
    public boolean canUpdate(String oldAction) {
        return !oldAction.equals("sleep");
    }

    @Override
    public String updateBehavior(String oldAction) {
        if (oldAction.equals("sleep")) {
            if (Game.getGameMap().getMapInfo().isNight()) return "idle";
            if (constraintWakeUp()) return "idle";
        }
        return oldAction;
    }

    protected abstract boolean constraintWakeUp();
}


