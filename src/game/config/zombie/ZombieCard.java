package game.config.zombie;

import com.google.common.collect.ImmutableSortedMap;
import com.google.gson.*;
import game.config.animation.Animations;
import game.config.ProtectiveCard;
import game.resourceUtil.JsonUtil;
import game.animation.data.AnimationData;
import game.animation.data.DataByJson;
import game.spirits.interfaces.Zombie;
import game.spirits.zombie.base.ZombieBodyData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.util.*;
import java.util.stream.Collectors;

public class ZombieCard {

    private static final Gson gson = new Gson();
    private static Logger logger = LoggerFactory.getLogger(DataByJson.class);

    private String name;

    private double maxHp;

    private Map<String, List<String>> actionMapping;

    private String animationKey;

    private ZombieBodyData zombieBodyData;

    private Constructor<? extends Zombie> zombieConstructor;

    private static Map<String, ZombieCard> zombieCardMap;

    static {
        zombieCardMap = new HashMap<>();
        JsonObject config = JsonUtil.jsonObjectByPath("json/config/zombie.json");
        for (Map.Entry<String, JsonElement> entry : config.entrySet()) {
            zombieCardMap.put(entry.getKey().toLowerCase(),
                    new ZombieCard(entry.getKey(), entry.getValue().getAsJsonObject()));
        }
    }

    public boolean canUseProtective(ProtectiveCard protectiveCard) {

        return protectiveCard.animationKey.equals(this.animationKey);
    }


    public ZombieCard(String name, JsonObject jsonObject) {
        this.name = name;
        this.maxHp = jsonObject.get("hp").getAsDouble();
        this.actionMapping = (Map) gson.fromJson(jsonObject.get("actionMapping"), HashMap.class);
        animationKey = jsonObject.get("animationKey").getAsString();
        String packagePath = "game.spirits.zombie.";
        if (jsonObject.get("packagePath") != null)
            packagePath = packagePath + jsonObject.get("packagePath").getAsString() + ".";
        String classPath;
        if (jsonObject.get("class") != null) {
            classPath = packagePath + jsonObject.get("class").getAsString();
        } else {
            classPath = packagePath + name;
        }
        try {
            zombieConstructor = ((Class) Class.forName(classPath)).getConstructor(double.class, double.class);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("创建异常");
        }
        initBodyData(jsonObject.get("status").getAsJsonObject());
    }

    public static ZombieCard zombieCard(String name) {

        ZombieCard result = zombieCardMap.get(name.toLowerCase());
        if (result == null) throw new RuntimeException("没有改僵尸" + name);
        return result;
    }

    public static Zombie newZombie(String name, double y, double x) {
        return zombieCard(name.toLowerCase()).newZombie(y, x);

    }

    public Zombie newZombie(double y, double x) {
        try {
            return zombieConstructor.newInstance(y, x);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private void initBodyData(JsonObject jsonObject) {
        ImmutableSortedMap.Builder<Double, List<String>> builder = ImmutableSortedMap.<Double, List<String>>
                orderedBy(Double::compare);
        Map<String, AnimationData> animationDataMap = new HashMap<>();
        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            JsonObject asJsonObject = entry.getValue().getAsJsonObject();
            double minHp = asJsonObject.get("minHp").getAsDouble();
            List<String> animations = (List) gson.fromJson(asJsonObject.get("animations"), ArrayList.class);
            List<String> animationKeys = animations.stream().map(i ->
                    Animations.cacheKey(animationKey, i)).collect(Collectors.toList());
            for (String animation : animationKeys) {
                animationDataMap.put(animation, Animations.getFromMap(animation));
            }


            builder.put(minHp, animationKeys);
        }
        ImmutableSortedMap<Double, List<String>> statusAnimationMap = builder.build();
        zombieBodyData = new ZombieBodyData(statusAnimationMap, animationDataMap);

    }


    public double getMaxHp() {
        return maxHp;
    }

    public ZombieBodyData getZombieBodyData() {
        return zombieBodyData;
    }

    public Map<String, String> newActionMapping() {
        Map<String, String> result = new HashMap<>(5);
        for (Map.Entry<String, List<String>> entry : actionMapping.entrySet()) {
            String useValue = entry.getValue().get(Double.valueOf(Math.random() * entry.getValue().size()).intValue());
            result.put(entry.getKey(), useValue);
        }
        return result;
    }
}
