package game.main.dialog.restart;

import game.main.dialog.Block;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import net.coobird.thumbnailator.Thumbnails;

import java.awt.image.BufferedImage;


public  class UpdateBtn extends Block {

    private static Image moneyImage;

    private static Image diamondImage;

    static {
        moneyImage = buildImage("pic/Cards/coinbag.png");
        diamondImage = buildImage("pic/Cards/diamond.png");
    }

    private boolean isMoney;

    private double num;


    public UpdateBtn(double beginX, double beginY, boolean isMoney, double num) {
        super(beginX, beginY, 35, 20);
        this.isMoney = isMoney;
        this.num = num;
        this.beginX = beginX;
        this.beginY = beginY;
    }

    public void draw(GraphicsContext graphicsContext) {

        graphicsContext.drawImage(geyImage(),
                beginX, beginY );
        graphicsContext.fillText(String.valueOf((int) num),
                beginX + geyImage().getWidth() + 5,  beginY + 15);

    }

    public Image geyImage() {
        if (isMoney) return moneyImage;
        return diamondImage;
    }



    private static Image buildImage(String path) {
        try {
            double scale = 0.8;
            if (path.contains("dia")) scale = 0.4;
            BufferedImage bufferedImage = Thumbnails.of(
                    SwingFXUtils.fromFXImage(
                            new Image(path), null)).scale(scale).asBufferedImage();
            return SwingFXUtils.toFXImage(
                    bufferedImage, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }


}
