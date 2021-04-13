package game.config;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import game.resourceUtil.JsonUtil;

import java.util.*;

public enum BaseBuff {
    银币(),
    金币(),
    钻石升级(),
    游戏加速(),
    工具(),
    熟练使用(),
    植物(),
    阳光(),
    restart();
    private static Gson gson = new Gson();
    private String description;
    private List<Double> cost;
    private byte maxLevel;
    private List<Double> values;

    private int nowLevel = 0;

    static {
        JsonObject config = JsonUtil.jsonObjectByPath("json/config/baseBuff.json");
        for (Map.Entry<String, JsonElement> entry : config.entrySet()) {
            String key = entry.getKey();
            BaseBuff.valueOf(key).init(entry.getValue().getAsJsonObject());
        }

    }

    public static void main(String[] args) {
        // BaseBuff restart = BaseBuff.restart;
        // for (BaseBuff value : BaseBuff.values()) {
        //     System.out.println(BaseBuff.阳光.getNowLevel());
        //     System.out.println(BaseBuff.阳光.getNowValue());
        // }
    }

    BaseBuff() {

    }

    private void init(JsonObject jsonObject) {

        description = jsonObject.get("description").getAsString();
        cost = (List) gson.fromJson(jsonObject.get("cost"), ArrayList.class);
        maxLevel = jsonObject.get("maxLevel") == null ? ((byte) cost.size()) : jsonObject.get("maxLevel").getAsByte();
        values = (List) gson.fromJson(jsonObject.get("values"), ArrayList.class);

    }


    public String getDescription() {
        return description;
    }

    public List<Double> getCost() {
        return cost;
    }

    public byte getMaxLevel() {
        return maxLevel;
    }

    public List<Double> getValues() {
        return values;
    }

    public int getNowLevel() {
        return nowLevel;
    }

    public double getNowValue() {
        return getValues().get(nowLevel);
    }
}

