package game.main.map;

import game.config.PlantCard;
import game.main.Game;
import game.spirits.interfaces.Plant;
import javafx.scene.image.Image;

/*

 */
public class MapLawn {
    private int col;

    private int row;

    private GameMap belongGameMap;

    private Plant plant;

    private double x;

    private double y;


    public MapLawn(GameMap gameMap, int col, int row) {
        this.col = col;
        this.row = row;
        this.belongGameMap = gameMap;
        this.x = MapUtil.getXByCol(gameMap.getTerrainName(), col);
        this.y = MapUtil.getYByRow(gameMap.getTerrainName(), row);

    }

    public boolean addPlant(Plant plant) {
        this.plant = plant;
        PlantCard plantCard = plant.plantCard();
        plant.to(this.getY() + plantCard.getShowOffSetY(),
                this.getX() + plantCard.getShowOffSetX());
        return true;
    }

    public boolean addPlant(PlantCard plantCard) {
        if (this.havePlant()) return false;
        this.plant = plantCard.newPlant(this);
        return true;
    }


    public void drawPlantIfHave() {
        Plant plant = getPlant();
        if (plant != null) {
            this.plant.draw();
        }
    }

    public void drawImage(Image image) {
        Game.getGraphicsContext().drawImage(image, x, y - image.getHeight());
    }

    public boolean havePlant() {
        return this.plant != null;
    }

    public Plant getPlant() {
        return plant;
    }

    public boolean removePlant() {
        if (!havePlant()) return false;
        this.plant = null;
        return true;

    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public GameMap getBelongGameMap() {
        return belongGameMap;
    }

    public MapInfo getMapInfo() {
        return belongGameMap.getMapInfo();
    }

    public void removePlantIfNeed() {
        Plant plant = getPlant();
        if (plant != null && plant.needRemove()) {
            removePlant();
        }
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public void draw() {
        drawPlantIfHave();
    }


}

