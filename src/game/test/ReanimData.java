package game.test;

import game.main.Game;
import game.resourceUtil.Fragment;
import game.resourceUtil.Resources;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Affine;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReanimData {



    Map<String, List<Fragment>> dataMap = new HashMap<>();

    public ReanimData(String path) {
//        json/zombie/BaseZombie.json
//        this.dataMap = dataMap;
    }
    public void initByFilePath(String path){
        // 很多代码
    }

    protected static String getActionKey(String action) {
        return "anim_" + action;
    }

    //
    protected int getFrameIndexInList(String key,int frame) {
        //  .....
        return 0;

    }
}
