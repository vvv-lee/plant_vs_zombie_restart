package game.cards;


import game.main.GameCards;
import game.main.map.GameMap;
import game.spirits.interfaces.Coordinate;
import javafx.scene.canvas.GraphicsContext;

import java.awt.image.BufferedImage;


/**
 * 顶部卡片
 * 包括种子和工具 如 铲子手套之类的卡片
 */

public abstract class AbstractCard implements Card, Coordinate {

    protected boolean haveSelect = false;

    protected double drawX;

    protected double drawY;

    protected int residue = -1;//剩余数量

    public AbstractCard(double drawY, double drawX) {
        this.drawX = drawX;
        this.drawY = drawY;

    }

    @Override
    public double getX() {
        return drawX;
    }

    @Override
    public double getY() {
        return drawY;
    }

    @Override
    public void notUse() {

    }

    @Override
    public boolean mouseOn(double mouseOnY, double mouseOnX) {
        return (getX() < mouseOnX)
                && (getX() + this.getImageWidth() > mouseOnX)
                && (mouseOnY > 0) && (mouseOnY < getImageHeight());
    }


    @Override
    public Card clickInMap(double mouseY, double mouseX, int btn) {
        boolean useSuccess;
        if (btn == 3) useSuccess = false;
        else useSuccess = effectToMap(mouseY, mouseX, btn);
        if (useSuccess) return successUse();
        notUse();
        if (needKeep()) return this;
        return null;
    }

    @Override
    public abstract void showInMap(double mouseY, double mouseX);

    @Override
    public abstract void draw();


    protected abstract double getImageHeight();

    protected abstract double getImageWidth();

    @Override
    public abstract void update();

    /**
     * 点击卡片返回所需的对象
     */
    @Override
    public abstract Card select();

    protected abstract Card successUse();

    protected abstract boolean canSelect();

    protected abstract boolean effectToMap(double mouseY, double mouseX, int bytn);

    /**
     * 使用之后是否继续在鼠标上
     */
    protected boolean needKeep() {
        return false;//使用之后就会取消 如铲子  而锤子不会取消
    }


}
