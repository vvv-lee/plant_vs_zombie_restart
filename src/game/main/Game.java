package game.main;

import game.cards.Card;
import game.config.animation.Animations;
import game.main.map.BaseNightMap;
import game.main.map.GameMap;
import game.main.mission.Mission;
import game.main.mission.Mission1;
import game.main.mission.factory.ZombieFactory;
import game.resourceUtil.Resources;
import game.spirits.interfaces.Zombie;
import javafx.scene.Cursor;
import javafx.scene.canvas.GraphicsContext;

import java.util.List;

public class Game {


    protected static GameMap gameMap;

    protected static GameCards gameCards;

    protected GameScene gameScene;

    protected int lastDrawFrame = -1;

    protected Card selectCard;

    protected Mission mission;


    protected GraphicsContext graphicsContext;


    public static boolean rouge = false;

    private static volatile Game game;

    /**
     * 其实没有多线程问题,象征性用一下
     */
    protected static Game getInstance() {
        if (game == null) {
            synchronized (Game.class) {
                if (game == null) {
                    init();
                }
            }
        }
        return game;
    }

    public static void init() {
        Resources.init();
        Animations.init();

        if (rouge) game = new RougeGame();
        else game = new Game();
        game.gameScene.setGame(game);

    }


    protected Game() {
        this.gameScene = new GameScene();
        this.graphicsContext = gameScene.getDrawCanvasCanvas().getGraphicsContext2D();
        Game.gameCards = new GameCards();
        gameMap = new BaseNightMap();
        this.mission = new Mission1();


//        for (PlantCard value : PlantCard.cardMap.values()) {
//
//        }
    }


    public int nextFrame(int needShowFrameNum) {
        if (lastDrawFrame < needShowFrameNum) {
            prepareNextFrame();
            lastDrawFrame++;
            return lastDrawFrame;
        }
        return lastDrawFrame;
    }

    protected void update() {
        gameMap.update();
        gameCards.update();
        List<List<Zombie>> newZombieList = mission.newZombie();
        for (int row = 0; row < newZombieList.size(); row++) {
            for (Zombie zombie : newZombieList.get(row)) {
                gameMap.addZombie(row, zombie);
            }
        }
    }

    protected void prepareNextFrame() {
        draw();
        update();
    }

    protected void draw() {
        gameMap.drawBackground();
        gameCards.draw();
        gameMap.draw();
    }


    public void click(double y, double x, int btn) {
        if (mouseInGameMap(y, x)) selectCard = gameMap.click(y, x, btn);
        else if (btn == 3) selectCard = null;
        else if (mouseInTopCards(y, x)) {
            if (selectCard != null) selectCard.notUse();
            selectCard = gameCards.click(x);
        }
    }


    public Cursor mouseMoved(double y, double x) {
        Cursor cursor = Cursor.DEFAULT;
        if (mouseInTopCards(y, x)) cursor = gameCards.mouseOn(x);
        else if (mouseInGameMap(y, x)) cursor = gameMap.mouseMoveWithCard(selectCard, y, x);
        return cursor;
    }

    public static GraphicsContext getGraphicsContext() {
        return getInstance().graphicsContext;
    }

    public static GameScene getGameScene() {
        return getInstance().gameScene;
    }


    public static GameMap getGameMap() {
        return gameMap;
    }

    protected boolean mouseInGameMap(double y, double x) {
        return (y >= 70);
    }


    protected boolean mouseInTopCards(double y, double x) {
        return y < 70 && x <= 1200 && x >= 150 && y > 0;
    }

    public static GameCards getGameCards() {
        return gameCards;
    }
}
