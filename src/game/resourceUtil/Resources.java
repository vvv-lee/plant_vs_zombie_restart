package game.resourceUtil;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Coordinate;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.MemoryCacheImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;
import java.util.List;

public class Resources {

    public static void init() {
        String fragmentPath = "pic/Material";
        File file = new File(getRealPath(fragmentPath));
        for (File childFile : Objects.requireNonNull(file.listFiles())) {
            String name = childFile.getName();
            Image image = new Image(fragmentPath + "/" + name);
            Fragment.fragmentCacheMap.put(name.replace(".png", "").toLowerCase(), image);
        }

    }


    public static URL getURL(String filePath) {
        return Resources.class.getClassLoader().getResource(filePath);
    }


    public static String getRealPath(String path) {
        try {
            URL u = Resources.getURL(path);
            return URLDecoder.decode(u.getFile(), "utf-8");
        } catch (Exception e) {
            System.out.println(path);
            throw new RuntimeException("文件路径获取异常");
        }
    }

    private static BufferedImage getBufferedImage(String path) {
        try {
            return ImageIO.read(
                    new FileImageInputStream(new File(getRealPath(path))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static List<BufferedImage> buildSeekList() {
        List<BufferedImage> seekList = new ArrayList<>();
        try {
            BufferedImage seekListImage = ImageIO.read(
                    new MemoryCacheImageInputStream(new FileInputStream(new File(Resources.getRealPath("pic/Cards/seeds.png")))));
            int i = 0;
            while (i < 9) {
                BufferedImage bufferedImage = null;

                bufferedImage = Thumbnails.of(seekListImage)
                        .sourceRegion(50 * i, 0, 50, 70)
                        .scale(1).asBufferedImage();

                seekList.add(bufferedImage);
                i++;
            }
            return seekList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static SortedMap<Double, Image> getScreenBufferedImageMap() {
        SortedMap<Double, Image> cdScreenMap = new TreeMap<>();
        double num = 70;
        for (int i = 1; i < num; i++) {
            int height = Double.valueOf(70 / num * i).intValue();
            BufferedImage topImage = new BufferedImage(50,
                    height, BufferedImage.TYPE_INT_ARGB);
            BufferedImage bottomImage = new BufferedImage(50,
                    70 - height, BufferedImage.TYPE_INT_ARGB);
            BufferedImage baseImage = new BufferedImage(50,
                    70, BufferedImage.TYPE_INT_ARGB);
            for (int y = 0; y < topImage.getHeight(); y++) {
                for (int x = 0; x < topImage.getWidth(); x++) {
                    topImage.setRGB(x, y, Color.black.getRGB());
                }
            }
            for (int y = 0; y < bottomImage.getHeight(); y++) {
                for (int x = 0; x < bottomImage.getWidth(); x++) {
                    bottomImage.setRGB(x, y, Color.black.getRGB());
                }
            }
            try {
                float opacity = 0.65f;
                BufferedImage need = Thumbnails.of(baseImage).scale(1)
                        .watermark(new Coordinate(0, 0), topImage, opacity)
                        .watermark(new Coordinate(0, topImage.getHeight()), bottomImage, 0.35f)
                        .asBufferedImage();
                cdScreenMap.put(i / num, SwingFXUtils.toFXImage(need, null));

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return cdScreenMap;
    }


    static BufferedImage add;
    static BufferedImage sunBank;
    static BufferedImage over;

    static {

        try {
            BufferedImage baseImage = getBufferedImage("pic/Cards/seedBank.png");

            sunBank = Thumbnails.of(baseImage).sourceRegion(0, 0, 80, baseImage.getHeight())
                    .scale(1).asBufferedImage();
            add = Thumbnails.of(baseImage).sourceRegion(200, 0, 50, baseImage.getHeight())
                    .scale(1).asBufferedImage();
            over = Thumbnails.of(baseImage)
                    .sourceRegion(baseImage.getWidth() - 10, 0, 10, baseImage.getHeight())
                    .scale(1).asBufferedImage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public static Image gameCardsBackgroundByNum(int num) {
        BufferedImage result = new BufferedImage(
                sunBank.getWidth() + add.getWidth() * num + over.getWidth(), sunBank.getHeight(),
                BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = result.getGraphics();
        graphics.drawImage(sunBank, 0, 0, null);
        int drawX = sunBank.getWidth();
        for (int i = 0; i < num; i++) {
            graphics.drawImage(add, drawX, 0, null);
            drawX = drawX + add.getWidth();
        }
        graphics.drawImage(over, drawX, 0, null);
        return SwingFXUtils.toFXImage(result, null);
    }

}
