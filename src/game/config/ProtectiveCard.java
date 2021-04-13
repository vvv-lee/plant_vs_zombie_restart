package game.config;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSortedMap;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import game.config.animation.Animations;
import game.resourceUtil.JsonUtil;
import game.resourceUtil.animation.data.AnimationData;
import game.resourceUtil.animation.data.DataByJson;
import game.spirits.protective.ZombieProtective;

import java.util.*;

public class ProtectiveCard {

    private static final Gson gson = new Gson();

    private String name;

    private double maxHp;

    public String animationKey;

    private static Map<String, ProtectiveCard> protectiveCardMap;

    private ImmutableSortedMap<Double, List<String>> statusAnimationMap;

    private Map<String, List<String>> clashMap = new HashMap<>();

    private Map<String, AnimationData> dataMap = new HashMap<>();


    static {
        protectiveCardMap = new HashMap<>();
        JsonObject config = JsonUtil.jsonObjectByPath("json/config/protective.json");
        for (Map.Entry<String, JsonElement> entry : config.entrySet()) {
            protectiveCardMap.put(entry.getKey().toLowerCase(),
                    new ProtectiveCard(entry.getKey(), entry.getValue().getAsJsonObject()));
        }
    }


    public static ProtectiveCard getProtectiveCardByName(String name) {
        return protectiveCardMap.get(name);
    }

    public ZombieProtective createNew() {

        return new ZombieProtective(this);
    }

    public ProtectiveCard(String name, JsonObject jsonObject) {
        this.name = name;
        this.maxHp = jsonObject.get("hp").getAsDouble();
        this.animationKey = jsonObject.get("animationKey").getAsString();
        initStatus(jsonObject.get("status").getAsJsonObject());
        initAnimation();
    }


    private void initStatus(JsonObject jsonObject) {
        ImmutableSortedMap.Builder<Double, List<String>> builder = ImmutableSortedMap.orderedBy(Comparator.comparing(Double::doubleValue));
        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            JsonObject asJsonObject = entry.getValue().getAsJsonObject();
            double minHp = asJsonObject.get("minHp").getAsDouble();
            List<String> animations = (List) gson.fromJson(asJsonObject.get("animations"), ArrayList.class);
            builder.put(minHp, animations);
        }
        statusAnimationMap=builder.build();
    }

    private void initAnimation() {
        for (List<String> strings : statusAnimationMap.values()) {
            for (String key : strings) {
                if (dataMap.containsKey(key)) continue;
                String realKey = Animations.cacheKey(animationKey, key);
                dataMap.put(key, Animations.getFromMap(realKey));
            }
        }
    }

    public double getMaxHp() {
        return maxHp;
    }


    public List<String> clashKeys(double value) {
        return clashKeys(aKeysByValue(value));
    }

    public List<AnimationData> showDataList(double value) {

        return showDataList(aKeysByValue(value));
    }

    public List<String> aKeysByValue(double value) {
        return statusAnimationMap.lowerEntry(value).getValue();
    }

    private List<String> clashKeys(List<String> keys) {
        if (keys.size() == 0) return Collections.emptyList();
        else if (keys.size() == 1) return clashMap.get(keys.iterator().next());
        ImmutableList.Builder<String> builder = ImmutableList.builder();
        for (String key : keys) {
            List<String> clashList = clashMap.get(key);
            if (clashList != null) {
                builder.addAll(clashList);
            }

        }
        return builder.build();
    }

    private List<AnimationData> showDataList(List<String> keys) {

        if (keys.size() == 0) return Collections.emptyList();
        else if (keys.size() == 1) return Collections.singletonList(
                dataMap.get(keys.iterator().next())
        );
        ImmutableList.Builder<AnimationData> builder = ImmutableList.builder();
        for (String key : keys) {
            builder.add(dataMap.get(key));

        }
        return builder.build();
    }

}
