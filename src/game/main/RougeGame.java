package game.main;

import game.main.dialog.Dialog;
import game.main.dialog.restart.RestartDialog;
import game.main.map.GameMap;
import javafx.scene.Cursor;
import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class RougeGame extends Game {


    public RougeGame() {
        super();
    }

    private Dialog showDialog;
//            = new RestartDialog(100, 0, 12, 8) ;

    @Override
    public int nextFrame(int needShowFrameNum) {
        return super.nextFrame(needShowFrameNum);
    }

    @Override
    public Cursor mouseMoved(double y, double x) {
        if (showDialog != null) return showDialog.mouseInDialog(y, x);
        return super.mouseMoved(y, x);
    }


    @Override
    protected void draw() {
        super.draw();
        if (showDialog != null) {
            showDialog.draw(getGraphicsContext());

        }

    }

    @Override
    protected void update() {
        if (showDialog == null) super.update();
    }
}
