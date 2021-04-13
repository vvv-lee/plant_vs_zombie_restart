package game.spirits.interfaces;


import game.main.Game;
import game.main.map.MapInfo;
import game.main.map.MapUtil;

public interface Lawn extends Coordinate {


    default Integer getRowNum() {
        return Game.getGameMap().getMapInfo().rowByY(this.getY());
    }

    default Integer getColNum() {
        return Game.getGameMap().getMapInfo().colByX(this.getX());
    }


    default boolean sameRow(Lawn lawn) {
        return this.getRowNum().equals(lawn.getRowNum());
    }

    default boolean sameCol(Lawn lawn) {
        return this.getColNum().equals(lawn.getColNum());
    }

    default boolean sameLawn(Lawn lawn) {
        return this.sameCol(lawn) && this.sameRow(lawn);
    }

}
