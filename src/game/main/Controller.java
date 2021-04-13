package game.main;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Cursor;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class Controller implements EventHandler<MouseEvent> {
    private Game game;
    private GameScene gameScene;

    public Controller(Game game, GameScene gameScene) {
        this.game = game;
        this.gameScene = gameScene;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {

        MouseButton button = mouseEvent.getButton();
        double y = mouseEvent.getY();
        double x = mouseEvent.getX();


        if (mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED) {
            int btn = 1;
            if (button == MouseButton.SECONDARY) btn = 3;
            else if (button == MouseButton.MIDDLE) btn = 2;
            game.click(y, x, btn);
        } else if (mouseEvent.getEventType() == MouseEvent.MOUSE_MOVED) {
            Cursor move = game.mouseMoved(y, x);
            gameScene.setCursor(move);
        }


    }
}


