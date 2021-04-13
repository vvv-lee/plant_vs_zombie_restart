package game.main.dialog;


import javafx.scene.Cursor;
import javafx.scene.canvas.GraphicsContext;

public interface Dialog {


    void click(int y, int x);

    boolean shouldClose();

    Cursor mouseInDialog(double y, double x);

    void draw(GraphicsContext graphics);


}
