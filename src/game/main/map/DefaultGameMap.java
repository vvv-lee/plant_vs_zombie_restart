package game.main.map;

import game.main.Game;
import game.spirits.interfaces.*;

import java.util.List;


public interface DefaultGameMap extends GameMap {
    @Override
    default boolean isNight() {
        return getMapInfo().isNight();
    }

    @Override
    default List<Recyclable> getRecyclables() {
        return getMapData().getRecyclableList();
    }

    @Override
    default List<List<Bullet>> getBulletLists() {
        return getMapData().getBulletsList();
    }

    @Override
    default List<List<Zombie>> getZombieQueueList() {
        return getMapData().getZombiesList();
    }

    @Override
    default List<List<MapLawn>> getMapLawnList() {
        return getMapData().getMapLawnsList();
    }

    @Override
    default String getTerrainName() {
        return getMapInfo().name();
    }

    @Override
    default void drawBackground() {
        Game.getGraphicsContext().drawImage(getMapInfo().background(), 0, 0);
    }




}
