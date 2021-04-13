package game.resourceUtil;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class JsonUtil {
    public static JsonObject jsonObjectByPath(String path) {

        return jsonObjectByRealPath(Resources.getRealPath(path));
    }

    public static JsonObject jsonObjectByRealPath(String path) {
        try {
            return JsonParser.parseReader(new FileReader(path)).getAsJsonObject();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
