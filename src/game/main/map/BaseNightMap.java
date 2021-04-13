package game.main.map;

public class BaseNightMap extends AbstractGameMap {
    @Override
    protected MapInfo buildMapInfo() {
         return new MapInfo("baseNight", true);
    }
}
