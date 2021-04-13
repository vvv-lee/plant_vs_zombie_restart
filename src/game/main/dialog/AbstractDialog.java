package game.main.dialog;

import javafx.scene.Cursor;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractDialog implements Dialog {
    protected byte widthNum;
    protected byte heightNum;
    protected double beginX;
    protected double beginY;
    protected double btnY;

    protected double leftX;

    protected double rightX;

    protected double topY;

    protected double bottomY;


    protected static Image leftImage = new Image("pic/Dialog/buttonLeft.png");
    protected static Image middleImage = new Image("pic/Dialog/buttonMiddle.png");
    protected static Image rightImage = new Image("pic/Dialog/buttonRight.png");
    String[][] showBackgroundPicsList;

    public AbstractDialog(double beginX, double beginY, int widthNum, int heightNum) {
        this.widthNum = (byte) (widthNum - 2);
        this.heightNum = (byte) (heightNum - 2);
        this.beginX = beginX;
        this.beginY = beginY;
        initShowDialogPic();

    }

    public static Map<String, Image> imageMap = new HashMap<>();

    static {
        imageMap.put("bl", new Image("pic/Dialog/bottomleft.png"));
        imageMap.put("bm", new Image("pic/Dialog/bottommiddle.png"));
        imageMap.put("br", new Image("pic/Dialog/bottomright.png"));
        imageMap.put("cl", new Image("pic/Dialog/centerleft.png"));
        imageMap.put("cm", new Image("pic/Dialog/centermiddle.png"));
        imageMap.put("cr", new Image("pic/Dialog/centerright.png"));
        imageMap.put("h", new Image("pic/Dialog/header.png"));
        imageMap.put("tl", new Image("pic/Dialog/topleft.png"));
        imageMap.put("tm", new Image("pic/Dialog/topmiddle.png"));
        imageMap.put("tr", new Image("pic/Dialog/topright.png"));
    }


    public abstract Cursor mouseInDialog(double y, double x);

    public void draw(GraphicsContext graphicsContext) {
        drawBackground(graphicsContext);
    }

    protected void drawBackground(GraphicsContext graphicsContext) {
        double y = beginY;
        double x = 0;
        y = y + imageMap.get("h").getHeight() - 20;
        for (String[] pics : showBackgroundPicsList) {
            x = beginX;
            for (String pic : pics) {
                Image image = imageMap.get(pic);
                graphicsContext.drawImage(image, x, y);
                x = x + image.getWidth();
            }
            y = y + imageMap.get(pics[0]).getHeight();
        }
        Image topImage = imageMap.get("h");

        graphicsContext.drawImage(topImage, (x + beginX) / 2 - imageMap.get("cm").getWidth(), beginY);

    }

    public void initShowDialogPic() {

        showBackgroundPicsList = new String[heightNum + 2][widthNum + 2];
        int i = 0;

        while (i < widthNum + 2) {
            showBackgroundPicsList[heightNum + 1][i] = "bm";
            showBackgroundPicsList[0][i] = "tm";
            i++;
        }
        i = 0;
        while (i < heightNum + 2) {
            showBackgroundPicsList[i][widthNum + 1] = "cr";
            showBackgroundPicsList[i][0] = "cl";
            i++;
        }
        showBackgroundPicsList[0][0] = "tl";
        showBackgroundPicsList[0][widthNum + 1] = "tr";
        showBackgroundPicsList[heightNum + 1][0] = "bl";
        showBackgroundPicsList[heightNum + 1][widthNum + 1] = "br";
        int row = 1;
        int col = 1;
        while (row < heightNum + 1) {
            while (col < widthNum + 1) {
                showBackgroundPicsList[row][col] = "cm";
                col++;
            }
            col = 1;
            row++;
        }
        double y = beginY;
        y = y + imageMap.get("h").getHeight() - 20;
        for (String[] pics : showBackgroundPicsList) {
            Image bufferedImage = imageMap.get(pics[0]);
            y = y + bufferedImage.getHeight();
        }
        int length = showBackgroundPicsList.length;
        btnY = y - imageMap.get(showBackgroundPicsList[length - 1][0]).getHeight() + 20;


        this.leftX = beginX + imageMap.get("bl").getWidth() * 0.3;
        this.rightX = beginX + imageMap.get("bl").getWidth()
                + widthNum * imageMap.get("bm").getWidth()
                + imageMap.get("br").getWidth() * 0.7;
        this.topY = beginY + imageMap.get("tl").getHeight() * 0.5;
        this.bottomY = beginY + imageMap.get("tl").getHeight()
                + heightNum * imageMap.get("cl").getHeight()
                + imageMap.get("bm").getWidth() * 0.7;

    }


    public abstract void click(int y, int x);

    public abstract boolean shouldClose();


}
