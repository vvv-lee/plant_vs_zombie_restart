package game.main;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.*;

public class GameScene extends Scene {

    public static final int gameWidth = 1416;

    public static final int gameHeight = 638;

    private static final int drawImageWidth = 1400;

    private static final int drawImageHeight = 600;

    private Canvas showCanvas = new Canvas(gameWidth, gameHeight);

    private Game belongGame;

    private AnimationTimer animationTimer;

    private int needShowFrameNum = 0;

    private static Pane gamePane = new Pane();


    public GameScene() {
        super(gamePane);

        showCanvas.widthProperty().bind(widthProperty());
        showCanvas.heightProperty().bind(heightProperty());
        gamePane.getChildren().addAll(showCanvas);

    }

    public void setGame(Game game) {
        this.belongGame = Game.getInstance();
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                belongGame.nextFrame(needShowFrameNum);
                needShowFrameNum++;
            }
        };
    }


    public void start(Stage stage) {
        Controller controller = new Controller(belongGame, this);
        stage.getScene().addEventHandler(MouseEvent.MOUSE_PRESSED, controller);
        stage.getScene().addEventHandler(MouseEvent.MOUSE_RELEASED, controller);
        stage.getScene().addEventHandler(MouseEvent.MOUSE_MOVED, controller);
//        Timer timer = new Timer("draw"); // 主流程控制
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
////                lastDrawFrameNum = belongGame.nextFrame(needShowFrameNum);
//
//            }
//        }, 0, 2);
        animationTimer.start();
    }

    public Canvas getDrawCanvasCanvas() {
        return showCanvas;

    }


}
