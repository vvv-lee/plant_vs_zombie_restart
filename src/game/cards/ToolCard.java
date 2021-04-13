package game.cards;

import game.main.Game;
import game.main.GameCards;
import game.main.map.GameMap;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 工具类卡片
 */
public abstract class ToolCard extends AbstractCard {

    private static Image bankImage = new Image("pic/Cards/toolBank.png");

    protected Image toolImage;

    @Override
    protected double getImageHeight() {
        return bankImage.getHeight();
    }

    @Override
    protected double getImageWidth() {
        return bankImage.getWidth();
    }

    @Override
    protected boolean effectToMap(double mouseY, double mouseX, int bytn) {
        return false;
    }

    public ToolCard( double y, double x, int residue) {
        super( y, x);
        toolImage = new Image("pic/Cards/" + this.getClass().getSimpleName() + ".png");
    }

    @Override
    public void showInMap( double mouseY, double mouseX) {
        Game.getGraphicsContext().drawImage(getToolImage(), mouseX - 36, mouseY - 36);
    }

    public Image getImage() {
        return bankImage;
    }


    @Override
    public void draw() {
        if (needBank())  Game.getGraphicsContext().drawImage(bankImage, drawX, drawY);
        Game.getGraphicsContext().drawImage(getToolImage(), drawX - 5, drawY - 5);
    }


    @Override
    public void update() {

    }


    public boolean needBank() {
        return true;
    }

    protected boolean showImage() {
        if (residue == 0) return false;
        return !haveSelect || residue != 1;
    }


    @Override
    public Card select() {
        if (!canSelect()) return null;
        haveSelect = true;
        return this;
    }

    private Image getToolImage() {
        return toolImage;
    }
}
