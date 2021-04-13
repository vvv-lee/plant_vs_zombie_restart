//package game.test;
//
//import com.google.gson.JsonArray;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
//import game.config.animation.Animations;
//import game.resourceUtil.Fragment;
//import game.resourceUtil.Resources;
//import game.resourceUtil.animation.data.AnimationData;
//import game.resourceUtil.animation.data.builder.DataBuilder;
//import game.resourceUtil.animation.data.DataByJson;
//
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.util.*;
//import java.util.function.BiFunction;
//
//public class DataByJsonBuilder extends AbstractDataBuilder {
//    JsonObject jsonObject;
//
//    @Override
//    public void readDataFromFile(String file) {
//        try {
//            jsonObject = JsonParser.parseReader
//                    (new FileReader(Resources.getRealPath(file))).getAsJsonObject();
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException("错误的读取文件,不存在" + file);
//        }
//    }
//
//    @Override
//    public boolean haveInitData() {
//        return jsonObject != null;
//    }
//
//    @Override
//    public DataByJson build() {
//        //非常多的代码
//
//    }
//}
