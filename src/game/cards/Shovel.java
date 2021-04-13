package game.cards;


import game.main.Game;
import game.main.GameCards;
import game.main.map.GameMap;
import game.main.map.MapLawn;
import game.spirits.recyclable.Sun;
import javafx.scene.image.Image;

import java.awt.image.BufferedImage;
import java.lang.reflect.Constructor;
import java.util.HashMap;


public class Shovel extends ToolCard {


    public Shovel() {
        super(0, 0, 0);

    }

    public Shovel(double y, double x, int residue) {
        super(y, x, residue);

    }

    @Override
    public Card select() {
        return this;
    }

    @Override
    protected Card successUse() {
        return null;
    }

    @Override
    protected boolean canSelect() {
        return false;
    }

    @Override
    protected boolean effectToMap(double mouseY, double mouseX, int btn) {
        MapLawn lawn = Game.getGameMap().getLawnByYX(mouseY, mouseX);
        return lawn.removePlant();
    }
}
