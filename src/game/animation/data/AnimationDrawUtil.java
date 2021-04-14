package game.animation.data;

import game.main.Game;
import game.resourceUtil.Fragment;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

public class AnimationDrawUtil {

    private static final Map<RenderingHints.Key, Object> renderingHintMap
            = Collections.singletonMap(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);

    public static void draw(
            Collection<Fragment> fragments,
            double drawX, double drawY,
            Set<String> images,
            Map<String, String> replaceMap) {
        boolean needFilter = images != null;
        boolean needReplace = replaceMap != null;
        for (Fragment fragment : fragments) {
            if (needFilter) {
                if (!images.contains(fragment.getImageName())) continue;
            }
            String imageName = fragment.getImageName();
            if (needReplace && replaceMap.containsKey(imageName)) {
                fragment.drawOtherImage(drawX, drawY, replaceMap.get(imageName));
            } else {
                fragment.drawImage(drawX, drawY);
            }
        }

    }


    public static void drawByImageCache(ImageDto dto, double drawX, double drawY) {
        GraphicsContext graphicsContext = Game.getGraphicsContext();
        graphicsContext.drawImage(dto.getImage(), drawX + dto.getOffSetX(), drawY + dto.getOffSetY());
    }

    public static BufferedImage newBufferedImage(int imageWidth, int imageHeight,int beginX, int beginY,  double dataHeight, double dataWidth) {
        if (imageHeight < 0) imageHeight = (int) dataHeight;
        if (imageWidth < 0) imageWidth = (int) dataWidth;
        imageHeight = imageHeight - beginY;
        imageWidth = imageWidth - beginX;

        return new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);
    }

//    public static Image buildOneFrameImage(AnimationData animationData,
//                                           String action, int frameNum,
//                                           int beginX, int beginY, int width, int height) {
//        if (height < 0) height = (int) animationData.height();
//        if (width < 0) width = (int) animationData.width();
//        height = height - beginY;
//        width = width - beginX;
//        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
//        Graphics2D graphics2D = (Graphics2D) bufferedImage.getGraphics().create();
//        graphics2D.setRenderingHints(renderingHintMap);
//        animationData.checkData(action, frameNum);
//        for (Fragment fragment : animationData.fragments(action, frameNum)) {
//            fragment.drawBufferedImage(graphics2D, -beginX, -beginY);
//        }
//        return SwingFXUtils.toFXImage(bufferedImage, null);
//    }


}
