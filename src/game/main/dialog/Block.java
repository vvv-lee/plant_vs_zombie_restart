package game.main.dialog;

public class Block {

    protected double beginX;
    protected double width;
    protected double beginY;
    protected double height;

    public boolean mouseOn(double y, double x) {
        return y >= beginY && y <= beginY + height && x >= beginX && x <= beginX + width;

    }

    public Block(double beginX, double beginY, double width, double height) {
        this.beginX = beginX;
        this.width = width;
        this.beginY = beginY;
        this.height = height;
    }

    public double getBeginX() {
        return beginX;
    }

    public double getWidth() {
        return width;
    }

    public double getBeginY() {
        return beginY;
    }

    public double getHeight() {
        return height;
    }
}
