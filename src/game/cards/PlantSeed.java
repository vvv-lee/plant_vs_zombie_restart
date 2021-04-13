package game.cards;

import game.config.PlantCard;
import game.main.Game;
import game.main.GameCards;
import game.main.map.GameMap;
import game.resourceUtil.Resources;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


import java.util.SortedMap;


/**
 * 选择植物的卡片
 */
public class PlantSeed extends AbstractCard {

    protected PlantCard plantCard;

    protected int cdTimer;

    public static SortedMap<Double, Image> cdScreenMap = Resources.getScreenBufferedImageMap();


    public PlantSeed(double y, double x, PlantCard plantCard) {
        super(y, x);
        this.plantCard = plantCard;
        cdTimer = plantCard.getMaxCd();
    }

    @Override
    protected double getImageHeight() {
        return plantCard.getCardImage().getHeight();
    }

    @Override
    protected double getImageWidth() {
        return plantCard.getCardImage().getWidth();
    }

    @Override
    public void notUse() {

    }

    @Override
    public void update() {
        if (isCd()) cdTimer--;

    }

    @Override
    public void showInMap(double mouseY, double mouseX) {
        Game.getGameMap().getLawnByYX(mouseY, mouseX).drawImage(plantCard.getOpacityImage());
        Game.getGraphicsContext().drawImage(plantCard.getPlantImage(), mouseX - 36, mouseY - 36);
    }


    @Override
    protected Card successUse() {
        cdTimer = plantCard.getMaxCd();
        return null;
    }

    @Override
    protected boolean effectToMap(double mouseY, double mouseX, int btn) {
        if (btn == 1) return Game.getGameMap().getLawnByYX(mouseY, mouseX)
                .addPlant(getPlantCard());
        return false;
    }

    @Override
    public void draw() {
        Game.getGraphicsContext().drawImage(plantCard.getCardImage(), drawX, drawY);
        if (isCd()) {
            Game.getGraphicsContext().drawImage(cdScreenMap.get(getKeyByCd()), drawX, drawY);
        }

    }

    public PlantCard getPlantCard() {
        return plantCard;
    }

    @Override
    protected boolean canSelect() {
        if (residue == 0) return false;
        return !isCd() && Game.getGameCards().sunPowerEnough(this.getPlantCard().getSunPower());
    }

    @Override
    public PlantSeed select() {
        if (!canSelect()) return null;
        haveSelect = true;
        return this;
    }

    protected boolean isCd() {
        return cdTimer != 0;
    }

    protected Double getKeyByCd() {
        Double result = cdScreenMap.keySet().iterator().next();
        double cdValue = (double) cdTimer / plantCard.getMaxCd();

        for (Double time : cdScreenMap.keySet()) {

            if (cdValue <= time) {
                return result;
            }
            result = time;
        }
        return result;
    }


}

