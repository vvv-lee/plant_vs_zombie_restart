package game.config;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import game.config.animation.Animations;
import game.main.map.MapLawn;
import game.resourceUtil.JsonUtil;
import game.resourceUtil.Resources;
import game.animation.data.AnimationData;
import game.animation.data.DataByJson;
import game.animation.SingleAnimation;
import game.spirits.interfaces.Plant;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Coordinate;
import net.coobird.thumbnailator.geometry.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 植物卡片
 */
public class PlantCard {

    private static Logger logger = LoggerFactory.getLogger(DataByJson.class);

    private String enName;

    private String cnName;

    private int sunPower;

    private int maxCd;

    private double maxHp;

    private int showOffSetY;

    private int showOffSetX;


    public static List<BufferedImage> seekList = Resources.buildSeekList();

    private byte rarity;

    private String defaultAction;

    private Image cardImage;

    private Image plantImage;

    private Image opacityImage;

    private AnimationData mainAnimationData;


    private Constructor<? extends Plant> constructor;


    private static Gson gson = new Gson();


    public static Map<String, PlantCard> cardMap = new HashMap<>();

    private static String[] packageKeyWordList = new String[]{"shroom", "pult", "shoot"};

    public static PlantCard getPlantCardByName(String name) {
        PlantCard result = cardMap.get(name.toLowerCase());
        if (result == null) throw new RuntimeException("没有该植物" + name);
        return result;
    }

    public static Plant newPlant(String plantName, MapLawn belongMapLawn) {
        return getPlantCardByName(plantName).newPlant(belongMapLawn);
    }

    public Plant newPlant(MapLawn belongMapLawn) {
        try {
            return newPlant(constructor, belongMapLawn);
        } catch (Exception e) {
            logger.error("植物" + enName + cnName + "生产失败", e);
            throw new RuntimeException("植物生产失败");
        }
    }

    public static Plant newPlant(Constructor<? extends Plant> constructor, MapLawn belongMapLawn) throws Exception {
        return constructor.newInstance(belongMapLawn);
    }


    public SingleAnimation createAnimation(Plant plant) {
        return new SingleAnimation(plant, mainAnimationData);
    }

    /*
     *
     *往下是巨无聊的资源加载
     *
     */

    static {

        JsonObject configMap = JsonUtil.jsonObjectByPath("json/config/plant.json");
        for (Map.Entry<String, JsonElement> entry : configMap.entrySet()) {
            PlantConfig plantConfig = gson.fromJson(entry.getValue(), PlantConfig.class);
            PlantCard plantCard = new PlantCard(entry.getKey(), plantConfig);
            cardMap.put(plantCard.cnName.toLowerCase(), plantCard);
            cardMap.put(plantCard.enName.toLowerCase(), plantCard);
        }

    }


    public PlantCard(String enName, PlantConfig plantConfig) {
        this.cnName = plantConfig.cnName;
        this.maxCd = (int) (plantConfig.cdSecond * 60);
        this.maxHp = plantConfig.maxHp;
        this.defaultAction = plantConfig.defaultAction;
        this.enName = enName;
        this.rarity = plantConfig.rarity;
        this.sunPower = plantConfig.sunPower;
        this.initClass(plantConfig.packageInfo);
        try {
            this.buildAnimationAndImage(plantConfig.imageConfig);
        } catch (Exception e) {
            logger.error("初始化" + enName + "动画图片失败", e);
            throw new RuntimeException("初始化动画图片失败");
        }


    }


    void initClass(String packageInfo) {
        String classPath = "game.spirits.plants";
        if (packageInfo == null) {
            String key = enName.toLowerCase();
            for (String keyWord : packageKeyWordList) {
                if (key.contains(keyWord)) {
                    packageInfo = keyWord;
                    break;
                }
            }
            if (packageInfo == null) packageInfo = "other";
        }
        try {
            String realClassPath = classPath + "." + packageInfo + "." + enName;
            Class<?> aClass = Class.forName(realClassPath);
            if (!Plant.class.isAssignableFrom(aClass)) {
                throw new RuntimeException("错误的植物类" + aClass);
            }
            if (Modifier.isAbstract(aClass.getModifiers())) {
                throw new RuntimeException("错误的植物类 不要用抽象class");
            }
            Class<? extends Plant> plantClass = (Class) aClass;
            this.constructor = plantClass.getConstructor(MapLawn.class);
        } catch (Exception e) {
            throw new RuntimeException("把植物所在包名写对" + e.getMessage());
        }

    }


    private void buildAnimationAndImage(ImageConfig imageConfig
    ) throws Exception {

        if (imageConfig == null) {
            imageConfig = new ImageConfig();
            imageConfig.action = defaultAction;
        }

        mainAnimationData = Animations.getByKeyAndFile(enName, imageConfig.animation);
        showOffSetX = imageConfig.offSetX;
        showOffSetY = imageConfig.offSetY;
        AnimationData useData;
        if (imageConfig.animation.equals("main")) useData = mainAnimationData;
        else useData = Animations.getByKeyAndFile(enName, imageConfig.animation);

        initImages(useData, imageConfig);
    }

    private void initImages(AnimationData useData, ImageConfig imageConfig) throws Exception {
        SourceRegion sourceRegion = imageConfig.sourceRegion;
        if (sourceRegion == null) sourceRegion = SourceRegion.defaultRegion;
        this.plantImage = useData.buildOneFrameImage(
                imageConfig.action, imageConfig.frameNum, sourceRegion.x, sourceRegion.y,
                sourceRegion.width, sourceRegion.height, null
        );

        this.cardImage = buildCardImage(plantImage, rarity, sunPower, imageConfig.cardConfig);
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(plantImage, null);
        BufferedImage bufferedOpacity = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D transparentGraphics = (Graphics2D) bufferedOpacity.getGraphics().create();
        transparentGraphics.setComposite(AlphaComposite.SrcOver.derive(0.5f));
        transparentGraphics.drawImage(SwingFXUtils.fromFXImage(plantImage, null), 0, 0, null);
        this.opacityImage = SwingFXUtils.toFXImage(bufferedOpacity, null);
    }

    private static final Font sunPowerFont = new Font("Tahoma", Font.BOLD, 12);

    private static final Color sunPowerFontColo = Color.black;

    private static Image buildCardImage(Image selectImage,
                                        int rarity,
                                        int sunPowerNum,
                                        ShowCardConfig cardConfig) throws Exception {
        if (cardConfig == null) cardConfig = ShowCardConfig.defaultCardConfig;
        String sunPower = String.valueOf(sunPowerNum);
        BufferedImage showImage = SwingFXUtils.fromFXImage(selectImage, null);
        Position position = new Coordinate(cardConfig.drawX, cardConfig.drawY);

        BufferedImage showPlantImage = Thumbnails.of(showImage).scale(cardConfig.scale).asBufferedImage();
        showImage = Thumbnails.of(seekList.get(rarity)).scale(1).watermark(
                position, showPlantImage, 1).asBufferedImage();

        Graphics graphics = showImage.getGraphics();
        Font sunPowerFont = PlantCard.sunPowerFont;
        Color sunPowerFontColo = PlantCard.sunPowerFontColo;
        graphics.setFont(sunPowerFont);
        graphics.setColor(sunPowerFontColo);
        int x = 10;
        if (sunPower.length() == 2) x = x + 6;
        if (sunPower.length() == 1) x = x + 10;
        graphics.drawString(sunPower, x, 65);
        return SwingFXUtils.toFXImage(showImage, null);
    }

    static class PlantConfig {
        private String cnName;
        private int sunPower;
        private double cdSecond;
        private byte rarity;
        private double maxHp;
        private String defaultAction = "idle";
        @SerializedName("package")
        private String packageInfo;
        @SerializedName("image")
        private ImageConfig imageConfig;
    }

    static class ImageConfig {
        private String animation = "main";
        private String action = "idle";
        private int frameNum = 0;
        private int offSetX = 0;
        private int offSetY = -10;
        @SerializedName("card")
        private ShowCardConfig cardConfig;
        private SourceRegion sourceRegion;
        private static ImageConfig imageConfig = new ImageConfig();


    }

    static class ShowCardConfig {
        private int drawY = 7;
        private int drawX = 2;
        private double scale = 0.57;
        private static ShowCardConfig defaultCardConfig = new ShowCardConfig();
    }

    static class SourceRegion {
        private int x = 0;
        private int y = 0;
        private int width = -1;
        private int height = -1;
        private static SourceRegion defaultRegion = new SourceRegion();
    }


    public Image getCardImage() {
        return cardImage;
    }

    public int getSunPower() {
        return sunPower;
    }

    public int getMaxCd() {
        return maxCd;
    }

    public double getMaxHp() {
        return maxHp;
    }

    public Image getPlantImage() {
        return plantImage;
    }

    public Image getOpacityImage() {
        return opacityImage;
    }

    public int getShowOffSetY() {
        return showOffSetY;
    }

    public int getShowOffSetX() {
        return showOffSetX;
    }

    public String getDefaultAction() {
        return defaultAction;
    }
}
