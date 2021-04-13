package game.resourceUtil.image;

import java.awt.image.BufferedImage;

public class ShowImageDto {
    private BufferedImage image;
    private double offsetX;
    private double offsetY;

    public BufferedImage getImage() {
        return image;
    }

    public ShowImageDto setImage(BufferedImage image) {
        this.image = image;
        return this;
    }


    public int getRoundOffsetX() {
        return Long.valueOf(Math.round(offsetX)).intValue();
    }
    public int getRoundOffsetY() {
        return Long.valueOf(Math.round(offsetY)).intValue();
    }
    public double getOffsetX() {
        return offsetX;
    }

    public ShowImageDto setOffsetX(double offsetX) {
        this.offsetX = offsetX;
        return this;
    }

    public double getOffsetY() {
        return offsetY;
    }

    public ShowImageDto setOffsetY(double offsetY) {
        this.offsetY = offsetY;
        return this;
    }

    @Override
    public String toString() {
        return "ShowImageDto{" +
                "image="  +
                ", offsetX=" + offsetX +
                ", offsetY=" + offsetY +
                '}';
    }
}
