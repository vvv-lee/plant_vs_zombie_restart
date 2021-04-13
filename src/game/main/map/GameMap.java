package game.main.map;

import game.cards.Card;
import game.config.PlantCard;
import game.main.Game;
import game.spirits.interfaces.Bullet;
import game.spirits.interfaces.Recyclable;
import game.spirits.interfaces.Zombie;
import javafx.scene.Cursor;
import javafx.scene.canvas.GraphicsContext;


import java.util.List;

public interface GameMap {



    Card click(double y, double x, int btn);

    default MapLawn getLawn(int row, int col) {
        return getMapLawnList().get(row).get(col);
    }

    default MapLawn getLawnByYX(double y, double x) {
        int row = MapUtil.getRow(getTerrainName(), y);
        int col = MapUtil.getCol(getTerrainName(), x);
        return getMapLawnList().get(row).get(col);
    }



    Cursor mouseMoveWithCard(Card selectCard, double mouseY, double mouseX);


    void drawBackground();

    void addRandomSumByNum(int num);

    List<Recyclable> update();

    void draw();

    boolean addPlant(PlantCard plantCard, int rowNum, int colNum);

    void addZombie(Zombie zombie);



    boolean isNight();

    List<Recyclable> getRecyclables();

    List<List<Bullet>> getBulletLists();

    List<List<Zombie>> getZombieQueueList();


    List<List<MapLawn>> getMapLawnList();

    String getTerrainName();

    MapData getMapData();

    MapInfo getMapInfo();

}
