package game.main.map;


import game.cards.Card;
import game.config.PlantCard;
import game.main.Game;
import game.spirits.interfaces.*;
import game.spirits.util.Production;
import game.spirits.zombie.*;
import javafx.scene.Cursor;


import java.util.*;


public abstract class AbstractGameMap implements DefaultGameMap {

    protected Game belongGame;
    protected Card selectCard;

    protected double mouseY;
    protected double mouseX;

    protected MapInfo mapInfo;
    protected MapData mapData;


    public AbstractGameMap() {
        mapInfo = buildMapInfo();
        mapData = new MapData(this);
        List<Zombie> zombieList = getZombieQueueList().get(3);
        zombieList.add(new ConeZombie(mapInfo.yByRow(0), 800));
    }


    protected abstract MapInfo buildMapInfo();


    @Override
    public Card click(double y, double x, int btn) {
        if (selectCard != null) {
            if (btn == 3) {
                selectCard.notUse();
                selectCard = null;
            } else {
                selectCard = selectCard.clickInMap(mouseY, mouseX, btn);
            }
            return selectCard;
        } else {
            for (Recyclable recyclable : getRecyclables()) {
                if (recyclable.mouseOn(y, x)) {
                    recyclable.beginRecycle();
                    break;
                }
            }
        }
        return null;
    }


    @Override
    public List<Recyclable> update() {
        return getMapData().update();
    }


    @Override
    public void draw() {
        getMapData().draw();
        if (selectCard != null) selectCard.showInMap(mouseY, mouseX);
    }

    @Override
    public Cursor mouseMoveWithCard(Card selectCard, double mouseY, double mouseX) {
        this.selectCard = selectCard;
        this.mouseY = mouseY;
        this.mouseX = mouseX;
        for (Recyclable recyclable : getRecyclables()) {
            if (recyclable.mouseOn(mouseY, mouseX)) return Cursor.HAND;
        }
        if (selectCard != null) return Cursor.NONE;
        return Cursor.DEFAULT;
    }


    @Override
    public void addRandomSumByNum(int num) {

    }

    @Override
    public boolean addPlant(PlantCard plantCard, int rowNum, int colNum) {
        return false;
    }

    @Override
    public void addZombie(Zombie zombie) {

    }

    @Override
    public MapData getMapData() {
        return mapData;
    }

    @Override
    public MapInfo getMapInfo() {
        return mapInfo;
    }


}

