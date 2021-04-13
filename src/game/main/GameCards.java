package game.main;

import game.cards.*;
import game.config.PlantCard;
import game.resourceUtil.Resources;
import javafx.scene.Cursor;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


import java.util.ArrayList;
import java.util.List;

/**
 * 上方可以选择的卡片集合
 * 终有一天 vajva 必将实现所有预言
 * 传送门
 * 手套
 * 卖钱
 * 自由组合
 */
public class GameCards {

    private double x = 200;

    private double y = 0;

    private List<PlantSeed> plantSeedList;

    private List<ToolCard> toolCardList;

    private byte slotNum = 10;

    private int sunPower = 10000000;

    private Image background;


    public GameCards() {
        plantSeedList = new ArrayList<>();
        toolCardList = new ArrayList<>();
        addCard("GatlingPeaShooter");
        addCard("DoomShroom");
        addCard("FumeShroom");
        addCard("GloomShroom");
        addCard("HypnoShroom");
        addCard("IceShroom");
        addCard("MagnetShroom");
        addCard("PuffShroom");
        addCard("ScaredyShroom");
        addCard("SeaShroom");
        addCard("SunShroom");
        addCard("Cattail");
        allowShovel();
        allowGlove();
        allowHammer();
    }

    private void addCard(String name) {
        addCard(PlantCard.getPlantCardByName(name));
    }

    private void addCard(PlantCard plantCard) {
        plantSeedList.add(new PlantSeed(
                y + 8, x + 75 + (50 * plantSeedList.size()), plantCard));
    }

    public void update() {
        plantSeedList.forEach(PlantSeed::update);
        toolCardList.forEach(ToolCard::update);
    }

    public void draw() {
        GraphicsContext graphicsContext = Game.getGraphicsContext();
        if (background == null) {
            background = Resources.gameCardsBackgroundByNum(slotNum);
        }
        graphicsContext.drawImage(background, x, y);
        plantSeedList.forEach(PlantSeed::draw);
        toolCardList.forEach(ToolCard::draw);
    }


    public Card click(double x) {
        Card card = this.returnMouseHoldCard(x);
        if (card != null) return card.select();
        return null;
    }

    public Cursor mouseOn(double x) {
        Card card = this.returnMouseHoldCard(x);
        return card == null ? Cursor.DEFAULT : Cursor.HAND;
    }

    private Card returnMouseHoldCard(double x) {
        for (PlantSeed plantSeed : plantSeedList) {
            if (plantSeed.mouseOn(50, x)) return plantSeed;
//            if (x > plantSeed.getX() && x <
//                    plantSeed.getX() +
//                            plantSeed.getPlantCard().getCardImage().getWidth()) return plantSeed;
        }
        for (ToolCard toolCard : toolCardList) {
            if (toolCard.mouseOn(50, x)) return toolCard;
//            if (x > toolCard.getX() && x <  toolCard.getX() +
//                            toolCard.getImage().getWidth()) return toolCard;
        }
        return null;
    }


    public boolean sunPowerEnough(int needSunPower) {
        return needSunPower <= this.sunPower;
    }

    public void allowHammer() {
        createNewToolCardToList(Hammer.class, -1);
    }

    public void allowShovel() {
        createNewToolCardToList(Shovel.class, -1);
    }

    public void allowGlove() {
        createNewToolCardToList(Glove.class, -1);
    }

    private void createNewToolCardToList(
            Class<? extends ToolCard> cardClass, int residue) {
        int index = needAddToIndex(cardClass);
        if (index == -1) return;
        double x = index * 80 + 800;
        try {
            ToolCard toolCard = cardClass.getDeclaredConstructor(
                 double.class, double.class, int.class
            ).newInstance( 8.0, x, residue);
            toolCardList.add(toolCard);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("createNewToolCardToList" + cardClass.toString());
        }
    }

    private int needAddToIndex(Class<? extends ToolCard> toolCardCass) {
        for (ToolCard toolCard : toolCardList) {
            if (toolCardCass == toolCard.getClass()) return -1;
        }
        return toolCardList.size();
    }
}
