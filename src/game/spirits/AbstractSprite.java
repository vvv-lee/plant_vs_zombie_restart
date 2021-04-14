package game.spirits;


import game.main.Game;
import game.resourceUtil.animation.Animation;
import game.spirits.interfaces.Sprite;
import game.spirits.util.Production;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.transform.Affine;

import java.util.*;


public abstract class AbstractSprite extends BaseDraw implements Sprite {

    protected int flashNum;


    protected double x;

    protected double y;

    protected String name = this.getClass().getSimpleName();

    protected String action;


    @Override
    public void to(double y, double x) {
        this.y = y;
        this.x = x;

    }

    public AbstractSprite() {
        setThisAnimation();
    }

    protected void setThisAnimation() {
        this.animation = buildAnimation();
    }

    @Override
    public Production doActionAndUpdate() {
        nextFrame();
        Production result = doAction();
        update();
        return result;
    }

    @Override
    public void draw() {
        if (flashNum <= 0) {
            super.draw();
            return;
        }
        GraphicsContext graphicsContext = Game.getGraphicsContext();
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(0.3);
        graphicsContext.setEffect(colorAdjust);
        super.draw();
        graphicsContext.setEffect(new ColorAdjust());
        flashNum--;
    }

    protected Production doAction()//变化
    {
        return new Production();
    }

    protected abstract void update();//变化

    public boolean setAction(String newAction) {
        if (Objects.equals(action, newAction)) return false;
        this.action = newAction;
        getAnimation().newAction(newAction);
        return true;
    }


    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }
}


