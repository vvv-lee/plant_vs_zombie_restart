package game.behavior.zombie.base;

import game.main.Game;
import game.main.map.MapInfo;
import game.main.map.MapLawn;
import game.spirits.interfaces.Plant;
import game.spirits.interfaces.Zombie;

import java.util.List;

public class ZombieBaseBehavior extends AbstractZombieBehaviorHandler {
    private Zombie zombie;

    public ZombieBaseBehavior(Zombie zombie) {
        this.zombie = zombie;
    }

    @Override
    public boolean canUpdate(String action) {
        return true;
    }

    protected boolean needDie() {
        return zombie.getHp() <= 0;
    }

    @Override
    public String updateBehavior(String action) {
        if (needDie()) return "die";


        MapLawn mapLawn = needEatPlant();
        if (mapLawn != null) {
            int row = mapLawn.getRow();
            int col = mapLawn.getCol();
            return "attack" + "-" + row + "-" + col;
        }
        if (action.equals("attack")) return null;

        return action;
    }

    protected MapLawn needEatPlant() {
        MapInfo mapInfo = Game.getGameMap().getMapInfo();
        int row = mapInfo.rowByY(zombie.getY());
        List<MapLawn> mapLawns = Game.getGameMap().getMapData().getMapLawnsList().get(row);
        for (MapLawn mapLawn : mapLawns) {
            Plant plant = mapLawn.getPlant();
            if (plant != null && zombie.crash(plant)) {
                return mapLawn;
            }
        }
        return null;

    }
}
