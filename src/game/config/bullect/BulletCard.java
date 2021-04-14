package game.config.bullect;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import game.config.animation.Animations;
import game.resourceUtil.JsonUtil;
import game.animation.data.AnimationData;
import game.spirits.bullet.LockBullet;
import game.spirits.bullet.TranslationalBullet;
import game.spirits.interfaces.Coordinate;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class BulletCard {


    private static Constructor<? extends TranslationalBullet> translationalConstructor;
    private static Constructor<? extends LockBullet> lockConstructor;

    static {
        try {
            translationalConstructor = TranslationalBullet
                    .class.getConstructor(BulletCard.class, double.class, double.class);

            lockConstructor = LockBullet
                    .class.getConstructor(BulletCard.class, double.class, double.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    private Config config;

    private AnimationData animationData;

    private static Map<String, BulletCard> protectiveCardMap = new HashMap<>();

    private static final Gson gson = new Gson();

    class Config {

        private double baseAttack;

        private double maxSpeed;

    }

    static {

        JsonObject configMap = JsonUtil.jsonObjectByPath("json/config/bullet.json");

        for (Map.Entry<String, JsonElement> entry : configMap.entrySet()) {
            String key = entry.getKey();

            BulletCard bulletCard = new BulletCard();
            bulletCard.config = gson.fromJson(entry.getValue(), Config.class);
            bulletCard.animationData = Animations.getFromMap(key.toLowerCase() + "-main");
            protectiveCardMap.put(key.toLowerCase(), bulletCard);
        }

    }


    public TranslationalBullet newTranslationalBullet(Coordinate coordinate) {
        return newTranslationalBullet(coordinate.getY(), coordinate.getX());
    }

    public TranslationalBullet newTranslationalBullet(double y, double x) {
        try {
            return lockConstructor.newInstance(this, y, x);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static TranslationalBullet newTranslationalBullet(String name, double y, double x) {
        return getCard(name).newTranslationalBullet(y, x);

    }


    public static BulletCard getCard(String key) {
        return protectiveCardMap.get(key.toLowerCase());
    }

    public double getBaseAttack() {
        return config.baseAttack;
    }

    public double getMaxSpeed() {
        return config.maxSpeed;
    }

    public AnimationData getAnimationData() {
        return animationData;
    }
}
