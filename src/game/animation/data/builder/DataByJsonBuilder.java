package game.animation.data.builder;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import game.resourceUtil.Fragment;
import game.resourceUtil.Resources;
import game.animation.data.DataByJson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.function.BiFunction;

public class DataByJsonBuilder extends AbstractDataBuilder {

    private JsonObject jsonObject;

    public DataByJsonBuilder(String path) {
        this(null, path);
    }

    public DataByJsonBuilder(String cacheKey, String path) {
        super(cacheKey, path);
    }

    public static DataByJsonBuilder newBuilder(String cacheKey, String path) {
        return new DataByJsonBuilder(cacheKey, path);
    }

    @Override
    public DataBuilder readDataFromFile(String file) {
        try {
            this.jsonObject = JsonParser.parseReader
                    (new FileReader(Resources.getRealPath(file))).getAsJsonObject();
            return this;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("错误的读取文件,不存在" + file);
        }
    }

    @Override
    public boolean haveInitData() {
        return jsonObject != null;
    }


    @Override
    protected DataByJson buildData() {

        return buildData(jsonObject, includeActions, includeImages, includeFrames, replaceMap)
                .setCrashWidth(width)
                .setCrashHeight(height).setKey(cacheKey).setCrashY(crashY).setCrashX(crashX);
    }

    private static DataByJson buildData(JsonObject jsonObject,
                                        Collection<String> includeActions,
                                        Collection<String> includeImages,
                                        Collection<Integer> includeFrames,
                                        Map<String, String> replaceMap
    ) {

        Map<String, List<List<Fragment>>> result = new HashMap<>();
        boolean needFilterAction = includeActions != null;
        boolean needFilterImage = includeImages != null;
        boolean needFilterFrame = includeFrames != null;
        boolean needReplaceImage = replaceMap != null;


        Map<String, List<Double>> speedMap = iteratorActionMap(jsonObject,
                (action, actionElements) -> {
                    if (needFilterAction && !includeActions.contains(action)) return (byte) 1;
                    List<List<Fragment>> frameList = result.computeIfAbsent(action, i -> new ArrayList<>());
                    JsonArray jsonArray = actionElements.getAsJsonArray();
                    List<Fragment> fragmentList = new ArrayList<>(jsonArray.size());
                    int i = -1;
                    for (JsonElement element : jsonArray) {
                        i++;
                        if (needFilterFrame && !includeFrames.contains(i)) continue;
                        JsonObject asJsonObject = element.getAsJsonObject();
                        JsonElement image = asJsonObject.get("i");
                        if (image != null) {
                            if (needFilterImage && !includeImages.contains(image.getAsString())) continue;
                        }
                        String imageName = asJsonObject.get("i").getAsString();
                        Fragment fragment = new Fragment(asJsonObject);
                        if (needReplaceImage) {
                            String newName = replaceMap.get(imageName);
                            if (newName != null) {
                                fragment.replaceImage(newName);
                            }
                        }
                        fragmentList.add(fragment);
                    }
                    frameList.add(fragmentList);
                    return (byte) 0;
                }
        );


        return new DataByJson(result, speedMap, null);
    }

    private static Map<String, List<Double>> iteratorActionMap(JsonObject jsonObject,
                                                               BiFunction<String,
                                                                       JsonElement,
                                                                       Byte>
                                                                       biFunction) {
        Map<String, List<Double>> speedMap = new HashMap<>();


        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            String action = entry.getKey();
            boolean haveSpeed = entry.getValue().getAsJsonObject().get("speedList") != null;


            JsonArray actionList = entry.getValue().getAsJsonObject().get("actionList").getAsJsonArray();
            Iterator<JsonElement> iterator = actionList.iterator();
            List<Double> speeds = new ArrayList<>();
            if (haveSpeed) {
                JsonArray speedList = entry.getValue().getAsJsonObject().get("speedList").getAsJsonArray();
                iterator = speedList.iterator();
            }

            boolean useAction = true;
            for (JsonElement actionElements : actionList) {
                Byte msg = biFunction.apply(action, actionElements);
                if (msg == 0) {
                    if (haveSpeed) speeds.add(iterator.next().getAsDouble());
                } else if (msg == 1) {
                    useAction = false;
                    break;
                } else if (msg == 2) return speedMap;
            }
            if (haveSpeed && useAction) speedMap.putIfAbsent(action, speeds);
        }
        return speedMap;
    }


}
