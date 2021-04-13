package game.main.map;



public class BaseLightMap extends AbstractGameMap {


    @Override
    protected MapInfo buildMapInfo() {
        return new MapInfo("baseLight", false);
    }
}

