package game.test;

import game.resourceUtil.Fragment;
import javafx.scene.image.Image;

import java.util.List;
import java.util.Map;

public class JsonData {

    private Map<String, List<Double>> speedMap;



    private Map<String, List<List<Fragment>>> fragmentMap;

    public JsonData(String path) {
//        json/zombie/BaseZombie.json
//        this.dataMap = dataMap;
    }
    public void initByFilePath(String path){
        // 很多代码
    }


    List<Fragment> getFragments(String action, int frame) {
        return fragmentMap.get(action).get(frame);

    }

    double xSpeed(String action, int frameNum) {
        return speedMap.get(action).get(frameNum);
    }


}
