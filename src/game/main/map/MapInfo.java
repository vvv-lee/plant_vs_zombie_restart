package game.main.map;

import javafx.scene.image.Image;

public class MapInfo {

    protected Image background;

    protected boolean isNight;

    protected String name;

    public MapInfo(String name, boolean isNight) {
        this.name = name;
        this.isNight = isNight;
        this.background = new Image("pic/Background/" + name + ".png");
    }

    public Image background() {
        return background;
    }

    public boolean isNight() {
        return isNight;
    }

    public String name() {
        return name;
    }

    public int maxRowNum() {
        return MapUtil.getRowArrayByName(name).length;
    }

    public int yByRow(int row) {
        return MapUtil.getYByRow(name, row);
    }

    public int rowByY(double y) {
        return MapUtil.getRow(name, y);
    }

    public int colByX(double x) {
        return MapUtil.getCol(name, x);
    }

}
