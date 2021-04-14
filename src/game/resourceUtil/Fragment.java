package game.resourceUtil;

import com.google.gson.JsonObject;
import game.main.Game;
import game.animation.OffSetDto;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Affine;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class Fragment {
    private byte rotate = 0;
    private double x = 0;
    private double y = 0;

    private double scaleX = 0;
    private double scaleY = 0;

    private double shearX = 0;
    private double shearY = 0;
    private float alpha = 1;

    private String imageName;

    private Image image;

    private BufferedImage bufferedImage;

    protected static Map<String, Image> fragmentCacheMap = new HashMap<>(2500);

    public Fragment() {
    }

    public static Fragment newFragmentReplaceImage(Fragment fragment, String newImageName) {
        Fragment result = new Fragment();
        result.rotate = fragment.rotate;
        result.x = fragment.x;
        result.y = fragment.y;
        result.scaleX = fragment.scaleX;
        result.scaleY = fragment.scaleY;
        result.shearX = fragment.shearX;
        result.shearY = fragment.shearY;
        if (newImageName == null) newImageName = fragment.imageName;

        result.imageName = newImageName;
        result.image = fragmentCacheMap.get(newImageName);
        if (fragment.bufferedImage != null) {
            if (!newImageName.equals(fragment.imageName)) {
                result.bufferedImage = SwingFXUtils.fromFXImage(result.image, null);
            } else {
                result.bufferedImage = fragment.bufferedImage;
            }
        }
        return result;
    }


    public void replaceImage(String newImageName) {
        this.imageName = newImageName;
        this.image = fragmentCacheMap.get(imageName);
        if (bufferedImage != null) {
            bufferedImage = SwingFXUtils.fromFXImage(image, null);
        }

    }

    public void build(
            float x, float y,
            float sx, float sy,
            float kx, float ky, float alpha) {
        this.x = x;
        this.y = y;
        while (kx > 270) kx = kx - 360;
        while (ky > 270) ky = ky - 360;
        while (kx < -90) kx = kx + 360;
        while (ky < -90) ky = ky + 360;
        this.rotate = 0;
        while (kx >= 45 && ky >= 45) {
            kx = kx - 90;
            ky = ky - 90;
            rotate++;
        }
        int reverse = 1;


        if (kx - ky >= 180) {
            kx = 90 - Math.abs(kx - 90);
            reverse = -1;
        }
        double degreeX = kx / 180.0 * Math.PI;
        double degreeY = ky / 180.0 * Math.PI;
        this.shearX = -Math.tan(degreeY);
        this.shearY = Math.tan(degreeX);
        this.scaleX = sx * Math.abs(Math.cos(degreeX)) * reverse;
        this.scaleY = sy * Math.abs(Math.cos(degreeY));
        this.alpha = alpha;
    }


    public Fragment(JsonObject jsonObject) {
        float x = jsonObject.get("x") == null ? 0 : jsonObject.get("x").getAsFloat();
        float y = jsonObject.get("y") == null ? 0 : jsonObject.get("y").getAsFloat();
        float sx = jsonObject.get("sx") == null ? 1 : jsonObject.get("sx").getAsFloat();
        float sy = jsonObject.get("sy") == null ? 1 : jsonObject.get("sy").getAsFloat();
        float kx = jsonObject.get("kx") == null ? 0 : jsonObject.get("kx").getAsFloat();
        float ky = jsonObject.get("ky") == null ? 0 : jsonObject.get("ky").getAsFloat();
        float alpha = jsonObject.get("a") == null ? 1 : jsonObject.get("a").getAsFloat();
        this.imageName = jsonObject.get("i").getAsString().toLowerCase();
        image = fragmentCacheMap.get(imageName);


        this.build(x, y, sx, sy, kx, ky, alpha);
    }

    public void drawOtherImage( double offSetX, double offSetY, String otherImage) {
        GraphicsContext graphicsContext = Game.getGraphicsContext();

        Image drawImage = image;
        if (otherImage != null) drawImage = fragmentCacheMap.get(otherImage);
        double drawX = offSetX + x;
        double drawY = offSetY + y;

        Affine transform = graphicsContext.getTransform();
        transform.appendRotation(rotate * 90, drawX, drawY);
        transform.appendShear(shearX, shearY, drawX, drawY);
        transform.appendScale(scaleX, scaleY, drawX, drawY);
        graphicsContext.setTransform(transform);
        double oldGlobalAlpha = graphicsContext.getGlobalAlpha();



        graphicsContext.setGlobalAlpha(alpha * oldGlobalAlpha);
        graphicsContext.drawImage(drawImage, drawX, drawY);
        graphicsContext.setGlobalAlpha(1);
        graphicsContext.setTransform(new Affine());
        graphicsContext.restore();
        graphicsContext.setGlobalAlpha(oldGlobalAlpha);
    }

    public void drawImage( double offSetX, double offSetY) {
        drawOtherImage( offSetX, offSetY, null);

    }

    public void drawBufferedImage(Graphics2D graphics2D, int offSetX, int offSetY) {
        AffineTransform at = AffineTransform.getTranslateInstance(offSetX + x, offSetY + y);
        at.rotate(Math.PI / 2 * rotate);
        at.shear(shearX, shearY);
        at.scale(scaleX, scaleY);
        if (bufferedImage == null) bufferedImage = SwingFXUtils.fromFXImage(image, null);
        graphics2D.drawImage(bufferedImage, at, null);
    }

    public Image getImage() {
        return image;
    }

//    public static void main(String[] args) throws Exception {
//        double kx = 45;
//        double ky = 45;
//        while (kx < 0) kx = kx + 360;
//        while (ky < 0) ky = ky + 360;
//        int rotate = 0;
//        while (kx >= 45 && kx >= 45) {
//            kx = kx - 90;
//            ky = ky - 90;
//            rotate++;
//        }
//        double degreeX = kx / 180.0 * Math.PI;
//        double degreeY = ky / 180.0 * Math.PI;
//
//        BufferedImage edit = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
//        for (int x = 0; x < edit.getWidth(); x++) {
//            for (int y = 0; y < edit.getHeight(); y++) {
//                edit.setRGB(x, y, Color.blue.getRGB());
//            }
//        }
//        double drawX = 200;
//        double drawY = 200;
//        double shearX = -Math.tan(degreeY);
//        double shearY = Math.tan(degreeX);
//        double scaleY = 0.3;
//        double scaleX = 0.8;
//        double height = edit.getHeight();
//        double width = edit.getWidth();
//
//        int i = 0;
//        double newWidth = width * scaleX + Math.abs(shearX) * height * scaleY;
//        double newHeight = height * scaleY + Math.abs(shearY) * width * scaleX;
//
//
//        double xMove = shearX * height * scaleY;
//        double yMove = shearY * width * scaleX;
//        if (xMove > 0) xMove = 0;
//        if (yMove > 0) yMove = 0;
//        double beginX = drawX + xMove;
//        double beginY = drawY + yMove;
//
//
//        double overX = beginX + newWidth;
//
//        double overY = beginY + newHeight;
//
//
//        BufferedImage base = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
//
//
//        int minx = Math.min((int) beginX, (int) overX);
//        int maxX = Math.max((int) beginX, (int) overX);
//        int miny = Math.min((int) beginY, (int) overY);
//        int maxy = Math.max((int) beginY, (int) overY);
//        for (int x = minx; x < maxX; x++) {
//            base.setRGB(x, miny, Color.red.getRGB());
//            base.setRGB(x, maxy - 1, Color.red.getRGB());
//            for (int y = miny; y < maxy; y++) {
//                if (x == minx || x == maxX - 1) {
//                    base.setRGB(x, y, Color.red.getRGB());
//                }
//            }
//        }
//        Graphics2D g = (Graphics2D) base.getGraphics().create();
//        AffineTransform affineTransform = AffineTransform.getTranslateInstance(drawX, drawY);
//        affineTransform.rotate(rotate * Math.PI / 2);
//        affineTransform.shear(shearX, shearY);
//        affineTransform.scale(scaleX, scaleY);
//        g.drawImage(edit, affineTransform, null);
//        Thumbnails.of(base).scale(1).toFile("D://test4/2.png");
//    }

    public OffSetDto thisFragmentDto() {

        double drawX = x;
        double drawY = y;
        double width = image.getWidth();
        double height = image.getHeight();
        int i = 0;
        while (i < rotate) {
            double utilHeight = height;
            height = width;
            width = -utilHeight;
            i++;
        }

        double translateX = shearX * height * scaleY;
        double translateY = shearY * width * scaleX;
        if (translateY < 0) translateY = 0;
        if (translateX < 0) translateX = 0;


        double newWidth = shearX * height * scaleY + width * scaleX;
        double newHeight = -shearY * width * scaleX + height * scaleY;

        double beginX = drawX + translateX;

        double overX = beginX + newWidth;

        double beginY = drawY + translateY;
        double overY = beginY + newHeight;


        OffSetDto offSetDto = new OffSetDto();

        offSetDto.setMinX(Math.min(beginX, overX));
        offSetDto.setMaxX(Math.max(beginX, overX));

        offSetDto.setMinY(Math.min(beginY, overY));
        offSetDto.setMaxY(Math.max(beginY, overY));

//        if(newWidth<0){
//            System.out.println(width);
//            System.out.println(newWidth);
//        }

        return offSetDto;


    }

    public String getImageName() {
        return imageName;
    }

    @Override
    public String toString() {
        return "Fragment{" +
                "image=" + imageName +
                '}';
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}

